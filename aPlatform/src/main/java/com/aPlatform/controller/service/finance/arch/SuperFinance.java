package com.aPlatform.controller.service.finance.arch;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
public abstract class SuperFinance
{
	public Elements contents;
	public String[] parsingContainer;
	public String market;
	public Document doc;
}
