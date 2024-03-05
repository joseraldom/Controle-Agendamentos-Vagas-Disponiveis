package com.teste.pratico.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.services.SolicitanteService;

@Component
@FacesConverter(value = "solicitanteConverter", managed = true)
public class SolicitanteConverter implements Converter<SolicitanteDTO>  {

	@Autowired
    private SolicitanteService service;

    @Override
    public SolicitanteDTO getAsObject(FacesContext context, UIComponent component, String value) {
        return value == null || value.trim().isEmpty() ? null : service.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SolicitanteDTO value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }

        return "";
    }
}
