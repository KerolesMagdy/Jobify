package com.keroles.jobify.Config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Model.Entity.*;
import com.keroles.jobify.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

        prepareUsers();
        prepareRoles();
        prepareCountries();
        prepareCareerLevels();
        prepareDegreeLevels();
        prepareJobCategories();
        prepareJobTypes();
        prepareSkillsTools();
        prepareLanguages();
    }

    private void prepareCountries() throws IOException {
//        InputStream inputStream=new FileInputStream(new File("src/main/resources/data/countries.json"));

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
//        InputStream inputStream=new FileInputStream(new File("src/main/resources/data/language.json"));
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

//        InputStream inputStream=new FileInputStream(new File("src/main/resources/data/skills-tools.json"));
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
//        InputStream inputStream=new FileInputStream(new File("src/main/resources/data/degree_levels.json"));
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
//            InputStream inputStream=new FileInputStream(new File("src/main/resources/data/job_categories.json"));
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
//            InputStream inputStream=new FileInputStream(new File("src/main/resources/data/job_types.json"));
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

//            InputStream inputStream=new FileInputStream(new File("src/main/resources/data/user_roles.json"));
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
        if(usersRepo.findByEmail("keromagdy589@gmail.com")==null)
            usersRepo.save(
                    Users.builder().email("keromagdy589@gmail.com")
                            .password(passwordEncoder.encode("457889"))
                            .enabled(true)
                            .fullName("Keroles Magdy").build());
        if(usersRepo.findByEmail("karim@gmail.com")==null)
            usersRepo.save(
                    Users.builder().email("karim@gmail.com")
                            .password(passwordEncoder.encode("457889"))
                            .enabled(true)
                            .fullName("Keroles Magdy").build());
        if(usersRepo.findByEmail("mama@gmail.com")==null)
            usersRepo.save(
                    Users.builder().email("mama@gmail.com")
                            .password(passwordEncoder.encode("457889"))
                            .enabled(false)
                            .fullName("Keroles Magdy").build());
    }

}
