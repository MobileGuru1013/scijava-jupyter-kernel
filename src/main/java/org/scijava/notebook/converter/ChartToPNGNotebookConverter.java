/*-
 * #%L
 * SciJava polyglot kernel for Jupyter.
 * %%
 * Copyright (C) 2017 Hadrien Mary
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.scijava.notebook.converter;

import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.scijava.convert.Converter;
import org.scijava.notebook.converter.output.PNGImageNotebookOutput;
import org.scijava.plugin.Plugin;

@Plugin(type = Converter.class)
public class ChartToPNGNotebookConverter
		extends NotebookOutputConverter<Chart, PNGImageNotebookOutput> {

	@Override
	public Class<Chart> getInputType() {
		return Chart.class;
	}

	@Override
	public Class<PNGImageNotebookOutput> getOutputType() {
		return PNGImageNotebookOutput.class;
	}

	@Override
	public PNGImageNotebookOutput convert(Object object) {

		Chart chart = (Chart) object;

		byte[] bt;
		try {
			bt = BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);
			Base64.getEncoder().encodeToString(bt);
			return new PNGImageNotebookOutput(Base64.getEncoder().encodeToString(bt));
		} catch (IOException ex) {
			Logger.getLogger(ChartToPNGNotebookConverter.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;

	}

}
