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

    private String responseTopic = "mdtg/test/reply/%s";

    private Parameter parameters;

    @Getter
    @Setter
    static class Parameter {

        private String mapName;

        private List<String[]> points;
    }
}
