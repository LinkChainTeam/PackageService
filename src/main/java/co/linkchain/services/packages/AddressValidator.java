package co.linkchain.services.packages;

import co.linkchain.services.common.Address;
import co.linkchain.services.common.Location;
import co.linkchain.services.packages.http.GeocodioReceiver;
import co.linkchain.services.packages.http.PackageHttpListener;

public class AddressValidator {
    public static void validate(Address address){
        if(address == null){
            throw new RuntimeException("Impossible to complete address data");
        }
        GeocodioReceiver response = null;
        if(address.getAddress() == null){
            if(address.getLocation() != null){
                response =  PackageService.template.getForEntity("https://api.geocod.io/v1.3/reverse?q=" + address.getLocation().getLatitude() + "," + address.getLocation().getLongitude() + "&api_key=a336f6bf2bb3be4556e356b5354bc65a6bb436e", PackageHttpListener.GeocodioList.class).getBody().results.get(0);
            }else if(address.getFullvalue() != null){
                response =  PackageService.template.getForEntity("https://api.geocod.io/v1.3/geocode?q=" + address.getFullvalue() + "&api_key=a336f6bf2bb3be4556e356b5354bc65a6bb436e", PackageHttpListener.GeocodioList.class).getBody().results.get(0);
            }else{
                throw new RuntimeException("Impossible to complete address data");
            }

            address.setLocation(new Location(Double.toString(response.getLocation().getLat()), Double.toString(response.getLocation().getLng())));
            address.setFullvalue(response.getFormatted_address());
            address.setZip(response.getAddress_components().getZip());
            address.setCountry(response.getAddress_components().getCountry());
            address.setState(response.getAddress_components().getState());
            address.setCity(response.getAddress_components().getCity());
            address.setAddress(response.getAddress_components().getNumber() + " " + response.getAddress_components().getFormatted_street());
        }
    }
}
