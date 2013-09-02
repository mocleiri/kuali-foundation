package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.deploy.SysAdminExecutable;
import org.kuali.common.deploy.channel.spring.DefaultSecureChannelConfig;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DefaultSecureChannelConfig.class, SpringServiceConfig.class })
public class DefaultSysAdminConfig implements SysAdminConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	SecureChannel channel;

	@Override
	@Bean
	public Executable sysAdminExecutable() {
		boolean skip = env.getBoolean("sysadmin.skip", false);
		String hostname = env.getString("dns.hostname");
		UnixCmds cmds = new UnixCmds();
		List<String> commands = new ArrayList<String>();
		commands.add(cmds.hostname(hostname));
		commands.addAll(getCopyToolsJarToJreLibExt());
		return new SysAdminExecutable(channel, commands, skip);
	}

	/**
	 * Copy tools.jar to the jre/lib/ext directory. This enables advanced AppDynamics monitoring of the heap.
	 */
	protected List<String> getCopyToolsJarToJreLibExt() {
		UnixCmds cmds = new UnixCmds();

		// copy the jdk6 tools.jar to the jdk6 jre/lib/ext area
		String jdk6Src = env.getString("jdk6.tools.jar.src");
		String jdk6Dst = env.getString("jdk6.tools.jar.dst");
		String jdk6Cmd = cmds.cp(jdk6Src, jdk6Dst, true);

		// copy the jdk7 tools.jar to the jdk7 jre/lib/ext area
		String jdk7Src = env.getString("jdk7.tools.jar.src");
		String jdk7Dst = env.getString("jdk7.tools.jar.dst");
		String jdk7Cmd = cmds.cp(jdk7Src, jdk7Dst, true);

		return Arrays.asList(jdk6Cmd, jdk7Cmd);

	}

}