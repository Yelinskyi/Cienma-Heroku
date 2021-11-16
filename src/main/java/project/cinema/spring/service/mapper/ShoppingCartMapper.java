package project.cinema.spring.service.mapper;

import org.springframework.stereotype.Component;
import project.cinema.spring.dto.response.ShoppingCartResponseDto;
import project.cinema.spring.model.ShoppingCart;
import project.cinema.spring.model.Ticket;

import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> {

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
