package kr.co.hanbit.non_assignment;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HanbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanbitApplication.class, args);
	}

	// ModelMapper를 사용하기 위해 매번 new로 생성해야하기 때문에 Bean으로 미리 생성하여 의존성을 주입받아 사용하는 것이 성능상 더 유리
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);

		return modelMapper;
	}
}
