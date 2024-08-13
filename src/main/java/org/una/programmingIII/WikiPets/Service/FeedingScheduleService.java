package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Model.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedingScheduleService {

    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;

//    public FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
//        FeedingSchedule feedingSchedule = new FeedingSchedule(feedingScheduleDto);
//        feedingSchedule = feedingScheduleRepository.save(feedingSchedule);
//        return new FeedingScheduleDto(feedingSchedule);
//    }
//
//    public FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
//        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingScheduleDto.getId())
//                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found"));
//        feedingSchedule.update(feedingScheduleDto);
//        feedingSchedule = feedingScheduleRepository.save(feedingSchedule);
//        return new FeedingScheduleDto(feedingSchedule);
//    }
//
//    public FeedingScheduleDto getFeedingScheduleById(Long id) {
//        Optional<FeedingSchedule> feedingSchedule = feedingScheduleRepository.findById(id);
//        return feedingSchedule.map(FeedingScheduleDto::new).orElseThrow(() -> new RuntimeException("FeedingSchedule not found"));
//    }
//
//    public List<FeedingScheduleDto> getAllFeedingSchedules() {
//        List<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll();
//        return feedingSchedules.stream()
//                .map(FeedingScheduleDto::new)
//                .collect(Collectors.toList());
//    }

    public void deleteFeedingSchedule(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found"));
        feedingScheduleRepository.delete(feedingSchedule);
    }
}
