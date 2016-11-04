/*
 * Copyright 2016 Jonathan Paz <jonathan@pazdev.com>.
 *
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
 */
package com.pazdev.authserver.resources;

import com.pazdev.authserver.model.UploadedContent;
import com.pazdev.authserver.services.ResourceService;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@WebServlet(name = "UploadedResourceServlet", urlPatterns = {"/resource/*"})
public class UploadedResourceServlet extends HttpServlet {
    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");
    
    private final ResourceService rs;

    public UploadedResourceServlet(ResourceService rs) {
        this.rs = rs;
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String resourceID = requestURI.substring(9); // "/request/".length()
        if (UUID_PATTERN.matcher(resourceID).matches()) {
            UUID uuid = UUID.fromString(resourceID);
            UploadedContent content = rs.getResource(uuid);
            if (content != null) {
                response.setContentType(content.getMimeType());
                response.setContentLength(content.getContents().length);
                ServletOutputStream out = response.getOutputStream();
                out.write(content.getContents());
                out.flush();
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(404);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
