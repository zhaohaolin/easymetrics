package org.easymetrics.easymetrics.server.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymetrics.easymetrics.server.repository.MetricsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SaveRecordControllor extends BaseController {

	private static final String REQUEST_PARAMETER_RECORD = "record";

	private MetricsRepository metricsRepository;

	@RequestMapping(value = "/record/save.do", method = RequestMethod.POST)
	public String toEdit_get(HttpServletRequest _request,
			HttpServletResponse _response, Model model) {
		String record = _request.getParameter(REQUEST_PARAMETER_RECORD);
		if (record != null && !"".equals(record)) {
			metricsRepository.saveRecord(record);
		}
		return "/download/editFileInfo";
	}

	public void setMetricsRepository(MetricsRepository metricsRepository) {
		this.metricsRepository = metricsRepository;
	}

}
