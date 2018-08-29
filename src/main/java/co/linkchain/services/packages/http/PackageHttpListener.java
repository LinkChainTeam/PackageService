package co.linkchain.services.packages.http;

import co.linkchain.services.common.*;
import co.linkchain.services.common.Package;
import co.linkchain.services.packages.AddressValidator;
import co.linkchain.services.packages.PackageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PackageHttpListener {

    @RequestMapping(value = "/get/package", method = RequestMethod.GET)
    public Package get(@RequestParam(value="id") long id) {
        var request = PackageService.template.getForEntity("http://localhost:8087/manager/get?id=" + id, Package.class);
        return request.getBody();
    }

    @RequestMapping(value = "/get/location", method = RequestMethod.GET)
    public Location getLatestLocation(@RequestParam(value="id") long id) {
        var packagee = this.get(id);
        return packagee.getStates().get(packagee.getStates().size()-1).getAddress().getLocation();
    }

    @RequestMapping(value = "/create/package", method = RequestMethod.POST)
    public Package create(@RequestBody PackageBuilder pack){
        AddressValidator.validate(pack.receiver);
        var entity = PackageService.template.postForEntity("http://localhost:8087/manager/create/package", pack, Package.class);
        if(!entity.getStatusCode().is2xxSuccessful())
            return null;
        return entity.getBody();
    }

    @RequestMapping("/")
    public Address index(){
        var address = new Address();
        address.setFullvalue("49 elmwood pl, short hills, nj");
        AddressValidator.validate(address);
        return address;
    }

    @RequestMapping(value = "/create/state/{packId}", method = RequestMethod.POST)
    public PackageState addState(@PathVariable("packId") long id, @RequestBody PackageStateBuilder state){
        AddressValidator.validate(state.address);
        return PackageService.template.postForEntity("http://localhost:8087/manager/create/state/" + id, state, PackageState.class).getBody();
    }

    public static class GeocodioList{
        public ArrayList<GeocodioReceiver> results;

        public ArrayList<GeocodioReceiver> getResults() {
            return results;
        }

        public void setResults(ArrayList<GeocodioReceiver> results) {
            this.results = results;
        }
    }
}
