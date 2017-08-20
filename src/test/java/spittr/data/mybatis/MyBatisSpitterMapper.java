package spittr.data.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import spittr.domain.Spitter;

import java.util.List;

/**
 * Created by wenda on 8/20/2017.
 */
public interface MyBatisSpitterMapper {

    @Select("SELECT * FROM Spitter WHERE username=#{user}")
    Spitter findByUsername(@Param("user") String username);
//
//
//    @Update("UPDATE tbl_user SET sex = #{sex} WHERE userId = #{userId}")
//    void updateUserEmailById(@Param("userId") String userId, @Param("sex") String sex);
//
//    @Delete("DELETE FROM tbl_user WHERE userId = #{userId}")
//    void deleteUserById(@Param("userId") String userId);
//
//    void insertUser(User user);
//
//
//    List<Spitter> readByUsernameOrFullNameIgnoringCase   (String first, String last);

    @Select("select * from Spitter s where s.email like '%gmail.com'")
    List<Spitter> findAllGmailSpitters();

}
