package ladder.model;

import ladder.model.group.Rewards;
import ladder.model.move.Point;

import java.util.*;


public class Result {
    private final static String RESULT_ERROR = "결과가 없는 유저입니다.";
    private final static String RESULT_EMPTY_RESULT = "결과가 없습니다.";

    private Rewards rewards;
    private Map<String, Point> results;

    private Result(Map<String, Point> results, Rewards rewards){
        this.results = results;
        this.rewards = rewards;
    }

    public static Result of(Map<String, Point> results, Rewards rewards){
        if(results.isEmpty()){
            throw  new IllegalArgumentException(RESULT_EMPTY_RESULT);
        }
        return new Result(results, rewards);
    }

    public String getReward(String name){
        if(name.equals("all")){
            return results.entrySet().stream()
                    .map( entry -> entry.getKey() + " : " + rewards.getReward(entry.getValue()))
                    .reduce((x,y) -> x+"\n"+y)
                    .orElseThrow(() -> new IllegalArgumentException(RESULT_ERROR));
        }

        Point resultPoint = Optional.ofNullable(results.get(name))
                .orElseThrow(() -> new IllegalArgumentException(RESULT_ERROR));

        return rewards.getReward(resultPoint);
    }
}
