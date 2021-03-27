package ua.kharkiv.syvolotskyi.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PaginationUtils {
    private static final int DEFAULT_SIZE = 10;

    private PaginationUtils() {
    }

    public static int getOffset(HttpServletRequest request) {
        if (StringUtils.isNumeric(request.getParameter("offset"))) {
            int offset = Integer.parseInt(request.getParameter("offset"));
            if (offset > 0) {
                return offset;
            }
        }
        return 0;
    }
    public static int getSize(HttpServletRequest request) {
        if (StringUtils.isNumeric(request.getParameter("size"))) {
            int size = Integer.parseInt(request.getParameter("size"));
            if (size > 0) {
                request.setAttribute("size", size);
                return size;
            }
        }
        request.setAttribute("size", DEFAULT_SIZE);
        return DEFAULT_SIZE;
    }
}
