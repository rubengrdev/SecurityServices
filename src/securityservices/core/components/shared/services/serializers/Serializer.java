package securityservices.core.components.shared.services.serializers;

import securityservices.core.components.shared.exception.ServiceException;

public interface Serializer<T> {
    public String serialize( T object) throws ServiceException;
    public T unserialize( String data) throws ServiceException;
}