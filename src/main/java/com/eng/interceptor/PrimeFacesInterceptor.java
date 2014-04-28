package com.eng.interceptor;



	import java.io.IOException;
	import java.util.Collections;
	import java.util.Enumeration;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.TreeMap;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletRequestWrapper;
	import javax.servlet.http.HttpServletResponse;

	import org.springframework.web.servlet.HandlerInterceptor;
	import org.springframework.web.servlet.ModelAndView;

	public class PrimeFacesInterceptor implements HandlerInterceptor
	{

	    private static final String PF_INTERCEPTION_KEY = "PFIntercepted";

	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    {
	        try
	        {
	            if ((request.getAttribute(PF_INTERCEPTION_KEY) == null || Boolean.FALSE.equals(request.getAttribute(PF_INTERCEPTION_KEY))) && request.getHeader("Faces-Request") != null)
	            {

	                Map<String, String[]> additionalParams = null;
	                Enumeration<String> paramNames = request.getParameterNames();
	                if (paramNames != null)
	                {
	                    additionalParams = new HashMap<String, String[]>();
	                    while (paramNames.hasMoreElements())
	                    {
	                        String paramName = paramNames.nextElement();
	                        if (paramName.endsWith("_input"))
	                        {
	                            String[] val = request.getParameterValues(paramName);
	                            paramName = paramName.substring(0, paramName.indexOf("_input"));
	                            additionalParams.put(paramName, val);
	                        }
	                    }
	                }

	                PrimeFacesRequestWrapper wrappedRequest = new PrimeFacesRequestWrapper(request, additionalParams);

	                request.setAttribute(PF_INTERCEPTION_KEY, true);

	                request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(wrappedRequest, response);

	                return false;
	            }
	        }
	        catch (ServletException se)
	        {
	            se.printStackTrace();
	        }
	        catch (IOException ioe)
	        {
	            ioe.printStackTrace();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return true;
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	    {
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	    {
	    }

	    private class PrimeFacesRequestWrapper extends HttpServletRequestWrapper
	    {

	        private final Map<String, String[]> additionalParams;
	        private Map<String, String[]> allParameters = null;

	        public PrimeFacesRequestWrapper(final HttpServletRequest request, final Map<String, String[]> additionalParams)
	        {
	            super(request);
	            this.additionalParams = new TreeMap<String, String[]>();
	            if (additionalParams != null && additionalParams.size() > 0)
	            {
	                this.additionalParams.putAll(additionalParams);
	            }
	        }

	        @Override
	        public String getParameter(final String name)
	        {
	            String[] strings = this.getParameterMap().get(name);
	            if (strings != null)
	            {
	                return strings[0];
	            }
	            return null;
	        }

	        @Override
	        public Map<String, String[]> getParameterMap()
	        {
	            if (allParameters == null)
	            {
	                allParameters = new TreeMap<String, String[]>();
	                Map<String, String[]> originalParamMap = super.getParameterMap();
	                if (originalParamMap != null && originalParamMap.size() > 0)
	                {
	                    allParameters.putAll(originalParamMap);
	                }
	                allParameters.putAll(this.additionalParams);
	            }

	            return Collections.unmodifiableMap(allParameters);
	        }

	        @Override
	        public Enumeration<String> getParameterNames()
	        {
	            return Collections.enumeration(this.getParameterMap().keySet());
	        }

	        @Override
	        public String[] getParameterValues(final String name)
	        {
	            return this.getParameterMap().get(name);
	        }
	    }
	}



