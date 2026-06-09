package mdtg.modules.agent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import mdtg.modules.agent.entity.AgentPluginMapping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* 针对表【ai_agent_plugin_mapping(Agent与插件的唯一映射表)】的数据库操作Mapper
*/
@Mapper
public interface AgentPluginMappingMapper extends BaseMapper<AgentPluginMapping> {
    List<AgentPluginMapping> selectPluginsByAgentId(@Param("agentId") String agentId);
}




