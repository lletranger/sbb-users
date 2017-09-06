package org.tsys.sbb.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import javax.validation.constraints.Pattern;
import java.util.Date;

public @Data class DelayDto {

    private int board_id;

    @NotEmpty(message = "Delay time is compulsory")
    @Pattern(regexp = "([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}", message = "Delay time has invalid characters")
    private String delay;


    /**
     * Converts a given {@link DelayDto} for a {@link Board}
     * into a {@link Delay}
     *
     * @param delayDto {@link DelayDto}
     * @param board {@link Board}
     * @return a single {@link Delay}
     */
    public static Delay getDelayFromDto(DelayDto delayDto, Board board) {
        Delay delay = new Delay();
        //GMT+3 => -3
        Date date = new Date(DistanceAndTimeUtil.getDtoTime(delayDto.getDelay()) - 3*3600*1000);
        delay.setDelay_time(date);
        delay.setBoard(board);
        return delay;
    }
}
