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
                c.getIdent(),
                c.getEmail(),
                c.getPhone(),
                c.getAddress(),
                c.getDateOfBirth(),
                c.getPassword(),
                c.getClientCode(),
                c.getNumEquipments()
        );
    }

    public static JaxbClientDTO jaxbDtoFromClient(Client c) {
        return new JaxbClientDTO(
                c.getName(),
                c.getIdent(),
                c.getEmail(),
                c.getPhone(),
                c.getAddress(),
                c.getDateOfBirth(),
                c.getPassword(),
                c.getClientCode(),
                c.getNumEquipments()
        );
    }
}
