package securityservices.core.components.client.domain.services;

import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.shared.exception.BuildException;

public class ClientMapper {

    public static Client clientFromDTO(ClientDTO cdto) throws BuildException {
        return Client.getInstance(
                cdto.getName(),
                cdto.getIdent(),
                cdto.getEmail(),
                cdto.getPhone(),
                cdto.getAddress(),
                cdto.getBirthday(),
                cdto.getPassword(),
                cdto.getClientCode(),
                cdto.getNumEquipments()
        );
    }

    public static ClientDTO dtoFromClient(Client c) {
        return new ClientDTO(
                c.getName(),
                c.getEmail(),
                c.getDateOfBirth(),
                c.getPassword(),
                c.getPhone(),
                c.getIdent(),
                c.getAddress(),
                c.getClientCode(),
                c.getNumEquipments()
        );
    }
}
