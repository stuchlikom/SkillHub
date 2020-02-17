package com.wildcodeschool.skillhub.repository;
import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.entity.Expert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpertRepository implements CrudDao<Expert> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";
   
    
        @Override
        public List<Expert> findAll(Long filter) {

            Connection connection = null;
            PreparedStatement statementExpert = null;
            ResultSet resultSetExpert = null;
            PreparedStatement statementCategory = null;
            ResultSet resultSetCategory = null;  
    
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statementExpert = connection.prepareStatement("SELECT * FROM SkillHubDB.user where role = 'ROLE_EXPERT' or role = 'ROLE_ADMIN'");
                resultSetExpert = statementExpert.executeQuery();
    
                List<Expert> experts = new ArrayList<>();
                while (resultSetExpert.next()) {
                    Long userId = resultSetExpert.getLong("userId");
                    String name = resultSetExpert.getString("name");
                    String firstName = resultSetExpert.getString("firstName");                    
                    String nickName = resultSetExpert.getString("nickName");
                    
                    statementCategory = connection.prepareStatement("SELECT * FROM category c left join usercategory uc  on uc.categoryid = c.categoryid  where uc.userid = ?");
                    statementCategory.setLong(1, userId);
                    resultSetCategory = statementCategory.executeQuery();

                    System.out.print(">|" + userId + "||");
                    System.out.print(">|" + name + "||");
                    System.out.print(">|" + firstName + "||");
                    System.out.print(">|" + nickName + "||");

                    List<Category> categorys = new ArrayList<>();
                    // categorys.add(new Category (0L,"noch keine Kategorie"));
                    //System.out.print(">Init|" + categorys.get(0).getCategoryId() + "||");
                    //System.out.print(">Init|" + categorys.get(0).getCategoryName() + "||");

                    while (resultSetCategory.next()) {
                        Long categoryId = resultSetCategory.getLong("categoryId");
                        String categoryName = resultSetCategory.getString("categoryName");

                        categorys.add(new Category(categoryId, categoryName));
                        
                        System.out.print(">CatId|" + categoryId + "||");
                        System.out.print(">Name|" + categoryName + "||");

                    }

                    experts.add(new Expert(userId, name, firstName, nickName, categorys));
                                   
                    System.out.println("end while mit user "+userId+"|");
                }

               return experts;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
   

      
    @Override
    public Expert save(Expert user) {
        return null;
    }

    @Override
    public Expert findById(Long userId) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statementExpert = connection.prepareStatement("SELECT * FROM SkillHubDB.user WHERE userid = ?");
            statementExpert.setLong(1, userId);
            ResultSet resultSetExpert = statementExpert.executeQuery();

            if (resultSetExpert.next()) {

                PreparedStatement statementCategory = connection.prepareStatement("SELECT * FROM category c left join usercategory uc  on uc.categoryid = c.categoryid  where uc.userid = ?");
                statementCategory.setLong(1, userId);
                ResultSet resultSetCategory = statementCategory.executeQuery();  
            
                String name = resultSetExpert.getString("name");
                String firstName = resultSetExpert.getString("firstName");
                String nickName = resultSetExpert.getString("nickName");
                
                List<Category> categorysId = new ArrayList<>();

                while (resultSetCategory.next()) {
                    Long categoryId = resultSetCategory.getLong("categoryId");
                    String categoryName = resultSetCategory.getString("categoryName");

                    categorysId.add(new Category(categoryId, categoryName));
                }
                return new Expert(userId, name, firstName, nickName, categorysId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    
    @Override
    public Expert update(Expert user) {
        return null;
    }

    @Override
    public void deleteById(Long userid) {}

}
