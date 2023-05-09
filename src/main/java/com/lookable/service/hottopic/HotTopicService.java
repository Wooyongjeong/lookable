package com.lookable.service.hottopic;

import com.lookable.domain.hottopic.HotTopic;
import com.lookable.domain.hottopicanswer.Answer;
import com.lookable.domain.hottopicanswer.HotTopicAnswer;
import com.lookable.domain.user.User;
import com.lookable.dto.hottopic.request.HotTopicAnswerRequest;
import com.lookable.dto.hottopic.request.HotTopicQuestionRequest;
import com.lookable.dto.hottopic.response.HotTopicAnswerResponse;
import com.lookable.dto.hottopic.response.HotTopicQuestionResponse;
import com.lookable.exception.model.NotFoundException;
import com.lookable.repository.hottopic.HotTopicRepository;
import com.lookable.repository.hottopicanswer.HotTopicAnswerRepository;
import com.lookable.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HotTopicService {

    private final HotTopicRepository hotTopicRepository;
    private final HotTopicAnswerRepository hotTopicAnswerRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public HotTopicQuestionResponse getQuestion(String username, HotTopicQuestionRequest request) {
        User user = userService.findByUsername(username);

        int temperature = Integer.parseInt(request.getTemperature());
        List<HotTopic> hotTopics = hotTopicRepository.findByTemperatureFromLessThanEqualAndTemperatureToGreaterThanEqual(temperature, temperature);
        HotTopic hotTopic = getRandomHotTopic(hotTopics);

        HotTopicQuestionResponse response = HotTopicQuestionResponse.builder()
                .id(hotTopic.getId())
                .question(hotTopic.getQuestion())
                .build();
        if (hotTopicAnswerRepository.existsByHotTopicAndUser(hotTopic, user)) {
            response.setAnswer(getAnswerPercent(hotTopic));
        }
        return response;
    }

    private HotTopic getRandomHotTopic(List<HotTopic> hotTopics) {
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(hotTopics.size());
        return hotTopics.get(index);
    }

    @Transactional(readOnly = true)
    public HotTopicAnswerResponse getAnswerPercent(HotTopic hotTopic) {
        Integer yes = hotTopicAnswerRepository.countByHotTopicAndAnswer(hotTopic, Answer.Y);
        Integer no = hotTopicAnswerRepository.countByHotTopicAndAnswer(hotTopic, Answer.N);
        int total = yes + no;

        int Y = total != 0 ? yes / total * 100 : 0;
        int N = total != 0 ? no / total * 100 : 0;

        return HotTopicAnswerResponse.builder()
                .Y(Y)
                .N(N)
                .build();
    }

    @Transactional
    public HotTopicAnswerResponse addAnswerAndGetAnswerPercent(
            String username,
            Long hotTopicId,
            HotTopicAnswerRequest request
    ) {
        User user = userService.findByUsername(username);
        HotTopic hotTopic = hotTopicRepository.findById(hotTopicId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 실시간 핫토픽입니다."));
        HotTopicAnswer hotTopicAnswer = HotTopicAnswer.builder()
                .user(user)
                .hotTopic(hotTopic)
                .answer(Answer.valueOf(request.getAnswer()))
                .build();
        hotTopicAnswerRepository.saveAndFlush(hotTopicAnswer);
        return getAnswerPercent(hotTopic);
    }

}
