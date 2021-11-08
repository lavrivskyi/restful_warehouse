package warehouse.service.mapper;

public interface ResponseDtoMapper<T, D> {
    D mapToDto(T model);
}
