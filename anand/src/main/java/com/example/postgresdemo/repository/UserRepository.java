/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.User;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="SELECT n FROM User n WHERE n.userId = :userId")
	User getNoteById(@Param(value="userId") Long userId);
     

	@Query(value="select e.email_id,es.user_id  from user e "+" inner join email_scheduler es"+
	 " on es.user_id=e.user_id where e.user_id=:userId",nativeQuery=true)
	User findByUserId(@Param(value="userId") Integer integer);

	//@Query(value="SELECT n. *FROM user n where n.user_id = :userId",nativeQuery=true)
	//User findByUserId(@Param(value="userId")Integer userIdSearch);

	

}
