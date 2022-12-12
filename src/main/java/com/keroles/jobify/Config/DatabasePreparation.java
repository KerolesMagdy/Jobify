package com.keroles.jobify.Config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Model.Entity.*;
import com.keroles.jobify.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Component
public class DatabasePreparation implements CommandLineRunner {

    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private CareerLevelRepo careerLevelRepo;
    @Autowired
    private DegreeLevelRepo degreeLevelRepo;
    @Autowired
    private JobCategoryRepo jobCategoryRepo;
    @Autowired
    private JobTypeRepo jobTypeRepo;
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private SkillsToolsRepo skillsToolsRepo;
//    @Value("classpath:/data/skills-tools.json")
//    Resource resource;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;


    private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        prepareRoles();
        prepareCountries();
        prepareCareerLevels();
        prepareDegreeLevels();
        prepareJobCategories();
        prepareJobTypes();
        prepareSkillsTools();
        prepareLanguages();
        prepareUsers();
        prepareEmployers();
    }

    private void prepareCountries() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/countries.json");
        InputStream inputStream=resource.getInputStream();
        TypeReference<List<Country>> typeReference=new TypeReference<List<Country>>() {};
        countryRepo.deleteAll();
        for (Country c :
                objectMapper.readValue(inputStream,typeReference)) {
            countryRepo.save(c);
        }
    }

    private void prepareLanguages() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/language.json");
        InputStream inputStream=resource.getInputStream();
        TypeReference<List<Language>> typeReference=new TypeReference<List<Language>>() {};
        if(languageRepo.count()>0)
            languageRepo.deleteAll();
        for (Language l :
                objectMapper.readValue(inputStream,typeReference)) {
            languageRepo.save(l);
        }
    }


    private void prepareSkillsTools() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:data/skills-tools.json");
        InputStream inputStream=resource.getInputStream();

        TypeReference<List<SkillsTools>> typeReference=new TypeReference<List<SkillsTools>>() {};
        languageRepo.deleteAll();
        for (SkillsTools l :
                objectMapper.readValue(inputStream,typeReference)) {
            skillsToolsRepo.save(l);
        }
    }
    private void prepareCareerLevels() throws IOException {

//        InputStream inputStream=new FileInputStream(new File("src/main/resources/data/career_levels.json"));
        Resource resource = resourceLoader.getResource("classpath:data/career_levels.json");
        InputStream inputStream=resource.getInputStream();

        TypeReference<List<CareerLevel>> typeReference=new TypeReference<List<CareerLevel>>() {};
        careerLevelRepo.deleteAll();

        for (CareerLevel c :
                objectMapper.readValue(inputStream,typeReference)) {
            careerLevelRepo.save(c);
        }
    }
    private void prepareDegreeLevels() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/degree_levels.json");
        InputStream inputStream=resource.getInputStream();
        TypeReference<List<DegreeLevel>> typeReference=new TypeReference<List<DegreeLevel>>() {};
        degreeLevelRepo.deleteAll();
        for (DegreeLevel c :
                objectMapper.readValue(inputStream,typeReference)) {
            degreeLevelRepo.save(c);
        }
    }
    private void prepareJobCategories() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/job_categories.json");
        InputStream inputStream=resource.getInputStream();

        TypeReference<List<JobCategory>> typeReference=new TypeReference<List<JobCategory>>() {};
        jobCategoryRepo.deleteAll();
            for (JobCategory c :
                    objectMapper.readValue(inputStream,typeReference)) {
                jobCategoryRepo.save(c);
            }
    }
    private void prepareJobTypes() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/job_types.json");
        InputStream inputStream=resource.getInputStream();
        TypeReference<List<JobType>> typeReference=new TypeReference<List<JobType>>() {};
        jobTypeRepo.deleteAll();
            for (JobType c :
                    objectMapper.readValue(inputStream,typeReference)) {
                jobTypeRepo.save(c);
            }
    }
    private void prepareRoles() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:data/user_roles.json");
        InputStream inputStream=resource.getInputStream();
        TypeReference<List<UserRole>> typeReference=new TypeReference<List<UserRole>>() {};
            userRoleRepo.deleteAll();
            for (UserRole c :
                    objectMapper.readValue(inputStream,typeReference)) {
                userRoleRepo.save(c);
            }
    }
    private void prepareUsers(){
        if(usersRepo.findByEmail("keromagdy589@gmail.com".toCharArray())==null)
            usersRepo.save(
                    Users.builder().email("keromagdy589@gmail.com".toCharArray())
                            .password(passwordEncoder.encode("457889").toCharArray())
                            .enabled(true)
                            .fullName("Keroles Magdy".toCharArray()).build());
        if(usersRepo.findByEmail("karim@gmail.com".toCharArray())==null)
            usersRepo.save(
                    Users.builder().email("karim@gmail.com".toCharArray())
                            .password(passwordEncoder.encode("457889").toCharArray())
                            .enabled(true)
                            .fullName("Keroles Magdy".toCharArray()).build());
        if(usersRepo.findByEmail("mama@gmail.com".toCharArray())==null)
            usersRepo.save(
                    Users.builder().email("mama@gmail.com".toCharArray())
                            .password(passwordEncoder.encode("457889").toCharArray())
                            .enabled(false)
                            .fullName("Keroles Magdy".toCharArray()).build());
    }
    private void prepareEmployers(){
        if(!employerRepo.findByEmail("mina@gmail.com".toCharArray()).isPresent()){
            Company company=Company.builder()
                    .name("Code Solutions")
                    .address(new Address(null,new Country(1L,null),"ffff","ffff",454,true))
                    .description("ffffffffffffffffffffffff")
                    .empCountFrom(10)
                    .empCountTo(44)
                    .industry(new JobCategory(1L,null))
                    .build();
            employerRepo.save(
                    Employer.builder().email("mina@gmail.com".toCharArray())
                            .password(passwordEncoder.encode("457889").toCharArray())
                            .enabled(true)
                            .firstName("mina".toCharArray())
                            .lastName("mina".toCharArray())
                            .company(company)
                            .jobTitle("dsfgvcd")
                            .mobileNumber("01553384191")
                            .build());
        }
//        if(!employerRepo.findByEmail("ehab@gmail.com".toCharArray()).isPresent())
//            employerRepo.save(
//                    Employer.builder().email("ehab@gmail.com".toCharArray())
//                            .password(passwordEncoder.encode("457889").toCharArray())
//                            .enabled(true)
//                            .firstName("ehab".toCharArray())
//                            .lastName("ehab".toCharArray()).build());
    }

}
