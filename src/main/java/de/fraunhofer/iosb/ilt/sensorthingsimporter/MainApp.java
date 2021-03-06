/*
 * Copyright (C) 2017 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.sensorthingsimporter;

import de.fraunhofer.iosb.ilt.sensorthingsimporter.options.Option;
import de.fraunhofer.iosb.ilt.sensorthingsimporter.options.Parameter;
import de.fraunhofer.iosb.ilt.sta.ServiceFailureException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author scf
 */
public class MainApp {

	/**
	 * @param args the command line arguments
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ServiceFailureException
	 */
	public static void main(String[] args) throws URISyntaxException, IOException, MalformedURLException, ServiceFailureException {
		List<String> arguments = new ArrayList<>(Arrays.asList(args));
		if (arguments.isEmpty()) {
			showHelp();
			ImporterGui.main(args);
		} else if (arguments.contains("--help") || arguments.contains("-help") || arguments.contains("-h")) {
			showHelp();
			System.exit(0);
		} else {
			ImporterWrapper.importCmdLine(arguments);
		}
		System.exit(0);
	}

	public static void showHelp() {
		Options options = new Options();
		for (Option option : options.getOptions()) {
			StringBuilder text = new StringBuilder();
			for (String key : option.getKeys()) {
				text.append(key).append(" ");
			}
			for (Parameter param : option.getParameters()) {
				text.append("[").append(param.getName()).append("] ");
			}
			text.append(":\n");
			for (String descLine : option.getDescription()) {
				text.append("    ").append(descLine).append("\n");
			}
			System.out.println(text.toString());
		}
	}

}
