/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import javax.persistence.PersistenceException;

/**
 *
 * @author Colaborador
 */
public class DatabaseException extends SQLException {

    private String mensagem;

    private String getMensagem() {
        return mensagem;
    }

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Método responsável por receber a exceção e informar a mensagem de erro do
     * banco
     *
     * @param exception contendo o erro do banco
     */
    public DatabaseException(Exception exception) {

        if (exception instanceof SQLException) {
            SQLException exc = (SQLException) exception;
            Integer erro = exc.getErrorCode();
            switch (erro) {
                case 1062:
                    setMensagem("Tentativa de duplicidade de registos!");
                    break;
                case 1451:
                    setMensagem("Erro com integridade referencial!");
                    break;
                case 1022:
                    setMensagem("Tentativa de duplicidade de chave!");
                    break;
                case 1406:
                    setMensagem("Erro dados longos para a coluna!");
                    break;
                case 1408:
                    setMensagem("Erro Campo obrigatório não pode ser nulo!");
                    break;
                case 1048:
                    setMensagem("Campo obrigatório não informado");
                    break;

                default:
                    setMensagem("Erro ao persistir/Excluir os dados s");
                    break;
            }
        } else {
            if (exception instanceof PersistenceException) {
                setMensagem("Campo nulo não informado!!");
            } else {
                setMensagem("Erro ao persistir/Excluir os dados");
            }
        }

    }

    @Override
    public String getMessage() {
        return getMensagem();
    }
}
