package com.jobayour.modules.resumefunc.career;

import com.jobayour.modules.resumefunc.resume.UserResume;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareerService {

    private CareerRepository careerRepository;
    private final ModelMapper modelMapper;

    public CareerService(CareerRepository careerRepository, ModelMapper modelMapper) {
        this.careerRepository = careerRepository;
        this.modelMapper = modelMapper;
    }

    // 유저 아이디와 이력서번호 찾아서 해당하는 경력 전체 조회
    public List<CareerDTO> findCareerById(UserResume userResume) {
        List<Career> userCareerList = careerRepository.findByUserIdAndResumeNum(userResume.getUserId(), userResume.getResumeNum());
        List<CareerDTO> list = userCareerList.stream()
                .sorted(Comparator.comparing(Career::getUserCareerNum))
                .map(career -> modelMapper.map(career, CareerDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 유저 아이디와 이력서 번호, 경력 번호를 통해 경력 한개 조회
    public CareerDTO findCareerByIdAndNum(Career career) {
        Career userCareer = careerRepository.findByUserIdAndResumeNumAndUserCareerNum(career.getUserId(), career.getResumeNum(), career.getUserCareerNum());
        CareerDTO list = modelMapper.map(userCareer, CareerDTO.class);
        return list;
    }

    // 유저 경력 추가
    public void addCareer(Career careerInfo) {
        Career career = new Career();
        career.setUserId(careerInfo.getUserId());
        career.setResumeNum(careerInfo.getResumeNum());
        career.setCompany(careerInfo.getCompany());
        career.setWorking(careerInfo.getWorking());
        career.setEnterDt(careerInfo.getEnterDt());
        career.setEndDt(careerInfo.getEndDt());
        career.setPosition(careerInfo.getPosition());
        career.setCompanyOpen(careerInfo.getCompanyOpen());
        career.setResponseibilities(careerInfo.getResponseibilities());
        career.setSalary(careerInfo.getSalary());
        career.setArea(careerInfo.getArea());
        careerRepository.save(modelMapper.map(career, Career.class));
    }


    @Transactional
    public void modifyCareer(Career careerInfo) {
        Career career = careerRepository.findByUserIdAndResumeNumAndUserCareerNum(careerInfo.getUserId(), careerInfo.getResumeNum(), careerInfo.getUserCareerNum());
        career.setCompany(careerInfo.getCompany());
        career.setWorking(careerInfo.getWorking());
        career.setEnterDt(careerInfo.getEnterDt());
        career.setEndDt(careerInfo.getEndDt());
        career.setPosition(careerInfo.getPosition());
        career.setCompanyOpen(careerInfo.getCompanyOpen());
        career.setResponseibilities(careerInfo.getResponseibilities());
        career.setSalary(careerInfo.getSalary());
        career.setArea(careerInfo.getArea());
        careerRepository.save(modelMapper.map(career, Career.class));
    }

    @Transactional
    public void deleteCareer(Career career) {
        careerRepository.deleteCareerByUserIdAndResumeNumAndUserCareerNum(career.getUserId(), career.getResumeNum(), career.getUserCareerNum());
    }
}
