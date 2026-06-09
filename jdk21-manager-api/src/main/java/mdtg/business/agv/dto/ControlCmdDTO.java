package mdtg.business.agv.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author WangYunwei [2026-04-27]
 */
@Getter
@Setter
public class ControlCmdDTO {

    private String action = "/api/nav/patrol";

    private Boolean loop = true;

    private Integer rounds = 0;

    private Integer start_index = 0;

    private Parameter parameters;

    @Getter
    @Setter
    static class Parameter {

        private String map_name = "WHEELTEC";

        private List<Long[]> points;
    }
}
