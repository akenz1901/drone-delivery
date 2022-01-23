package com.drone;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class EDroneApplicationTests {
	@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() {
	}
	@Test
	void connectionToDatabase(){
		assertThat(dataSource).isNotNull();

		try(Connection connection = dataSource.getConnection()){
			assertThat(connection).isNotNull();
			assertThat(connection.getCatalog()).isEqualTo("dronedb");
		}catch(SQLException ex){
			log.info("Connection wasn't successful --> {}", ex.getLocalizedMessage());
		}
	}
}
