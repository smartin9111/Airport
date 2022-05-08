package hu.webuni.airport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.airport.config.AirportConfigProperties;

@Service
public class SpecialDiscountService implements DiscountService {

//	@Value("${airport.discount.special.limit}")
//	private int limit;
//	
//	@Value("${airport.discount.default.percent}")
//	private int defaulPercernt;
//	
//	@Value("${airport.discount.special.percent}")
//	private int specialPercent;
//	@Override
	
	@Autowired
	AirportConfigProperties config;
	
	public int getDiscountPercent(int totalPrice) {
		//return totalPrice > limit ? specialPercent : defaulPercernt;
		return totalPrice > config.getDiscount().getSpecial().getLimit() 
				? config.getDiscount().getSpecial().getPercent() 
				: config.getDiscount().getDef().getPercent();
	}

	
}
