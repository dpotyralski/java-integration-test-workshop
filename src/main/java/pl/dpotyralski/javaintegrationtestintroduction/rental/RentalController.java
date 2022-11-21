package pl.dpotyralski.javaintegrationtestintroduction.rental;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request.MovieDetailsRequest;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request.RentRequest;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request.RentalPriceCalculateRequest;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.response.RentalPriceResponse;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.response.RentalSummaryResponse;
import pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.response.ReturnMovieResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rentals")
@AllArgsConstructor
class RentalController {

    private final RentalFacade rentalFacade;

    @GetMapping
    @ApiOperation(value = "Customer rentals by customer Id and option to get only current/active or all rentals")
    public RentalSummaryResponse findCustomersRentals(@RequestParam Long byCustomerId,
            @RequestParam(required = false) boolean onlyActive) {

        List<RentalSummaryResponse.RentalResponse> rentalResponses =
                rentalFacade.findRentalsByCustomerId(byCustomerId, onlyActive).stream()
                        .map(this::getCustomerRentalResponse)
                        .toList();

        return new RentalSummaryResponse(rentalResponses);
    }

    @PostMapping
    @ApiOperation(value = "Customer rent movie endpoint")
    public RentalPriceResponse rentMovie(@RequestBody @Valid RentRequest rentRequest) {
        RentalCommand rentalCommand = convertToRentCommand(rentRequest);
        return new RentalPriceResponse(rentalFacade.rent(rentalCommand));
    }

    @PostMapping(path = "/price-calculation")
    @ApiOperation(value = "Rental price calculation endpoint")
    public RentalPriceResponse calculateRentalPrice(@RequestBody @Valid RentalPriceCalculateRequest rentalPriceCalculateRequest) {
        return new RentalPriceResponse(rentalFacade.calculateRentalPrice(convertToRentalPriceCalculateCommand(rentalPriceCalculateRequest)));
    }

    @PostMapping(path = "/{id}/return")
    @ApiOperation(value = "Rental return endpoint")
    public ReturnMovieResponse returnMovie(@PathVariable Long id) {
        ReturnCommand returnCommand = convertToReturnCommand(id);
        RentalDto rentalDto = rentalFacade.returnMovie(returnCommand);
        return new ReturnMovieResponse(rentalDto.getLateReturnCharge());
    }

    private RentalSummaryResponse.RentalResponse getCustomerRentalResponse(RentalDto rentalDto) {
        return new RentalSummaryResponse.RentalResponse(rentalDto.getId(), rentalDto.getPriceCalculated());
    }

    private RentalPriceCalculateCommand convertToRentalPriceCalculateCommand(RentalPriceCalculateRequest rentalPriceCalculateRequest) {
        return new RentalPriceCalculateCommand(map(rentalPriceCalculateRequest.getDetails()));
    }

    private RentalCommand convertToRentCommand(RentRequest rentRequest) {
        return new RentalCommand(rentRequest.getCustomerId(), map(rentRequest.getDetails()));
    }

    private ReturnCommand convertToReturnCommand(Long id) {
        return new ReturnCommand(id);
    }

    private List<RentalDetail> map(Set<MovieDetailsRequest> requestMoviesDetails) {
        return requestMoviesDetails.stream()
                .map(this::createMovieDetail)
                .toList();
    }

    private RentalDetail createMovieDetail(MovieDetailsRequest movie) {
        return new RentalDetail(movie.getMovieId(), movie.getDays());
    }

}
