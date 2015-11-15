/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import banco.DAOGenerico;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.EntidadeCidade;

/**
 *
 * @author Alunos
 */
@FacesConverter("converterCidade")
public class ConverterCidade implements Converter {

 @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                DAOGenerico dao=new DAOGenerico();
                Object estado=dao.recupera(EntidadeCidade.class, Long.parseLong(value));
                return estado;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((EntidadeCidade) object).getId());
        }
        else {
            return null;
        }
    }   

}
