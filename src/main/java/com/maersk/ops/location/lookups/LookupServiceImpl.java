package com.maersk.ops.location.lookups;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.model.BDA;
import com.maersk.ops.location.model.BDAType;
import com.maersk.ops.location.model.City;
import com.maersk.ops.location.model.CodeType;
import com.maersk.ops.location.model.Continent;
import com.maersk.ops.location.model.Country;
import com.maersk.ops.location.model.FacilityService;
import com.maersk.ops.location.model.FacilityType;
import com.maersk.ops.location.model.OlsonTimezone;
import com.maersk.ops.location.model.ServiceGroups;
import com.maersk.ops.location.model.State;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.BdaTypeRepo;
import com.maersk.ops.location.repository.CodeTypeRepo;
import com.maersk.ops.location.repository.OlsonTimeRepo;
import com.maersk.ops.location.repository.ServiceGrpRepo;
import com.maersk.ops.location.repository.TimezoneRepo;

@Service
public class LookupServiceImpl implements LookupService {
	
	@Autowired
	private TimezoneRepo timeRepo;
	
	@Autowired
	private OlsonTimeRepo olsonRepo;
	
	@Autowired
	private ServiceGrpRepo serviceGrpRepo;
	
	@Autowired
	private BdaTypeRepo bdaTypeRepo;
	
	@Autowired
	private CodeTypeRepo codeTypeRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TimezoneLookup> getTimezoneLookup() {
		List<Timezone> timeList = timeRepo.findAll();
		List<TimezoneLookup> timeLkpList = new ArrayList<TimezoneLookup>();
		if(timeList != null) {
			for(Timezone time : timeList) {
				timeLkpList.add(TimezoneLookup.builder().rowid(time.getRowid()).code(time.getCode()).name(time.getName())
						.dstName(time.getDst().getName()).build());
			}
		}
		return timeLkpList;
	}
	
	@Override
	public List<OlsonLookup> getOlsonLookup() {
		List<OlsonTimezone> olsonTimeList = olsonRepo.findAll();
		List<OlsonLookup> olsonLkpList = new ArrayList<OlsonLookup>();
		if(olsonTimeList!=null) {
			for(OlsonTimezone olson: olsonTimeList) {
				olsonLkpList.add(OlsonLookup.builder().rowid(olson.getRowid()).name(olson.getName()).build());
			}
		}
		return olsonLkpList;
	}
	
	@Override
	public List<ServiceGroupLookup> getServiceGroup() {
		List<ServiceGroups> serviceGrpList = serviceGrpRepo.findAll();
		List<ServiceGroupLookup> serviceGrpLkp = new ArrayList<ServiceGroupLookup>();
		if(serviceGrpList != null) {
			for(ServiceGroups serGrp : serviceGrpList ) {
				serviceGrpLkp.add(ServiceGroupLookup.builder().code(serGrp.getCode()).rowid(serGrp.getRowid()).build());
			}
		}
		return serviceGrpLkp;
	}
	
	@Override
	public List<CityLookup> getCity(String name, String status, String portFlag, String maerskCity){
		List<CityLookup> cities = new ArrayList<CityLookup>();
		if(name == null && status == null && portFlag == null && maerskCity == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(status != null) {
				query+="status='"+status+"' and ";
			}
			if(portFlag != null) {
				query+="port_Flag='"+portFlag+"' and ";
			}
			if(maerskCity != null) {
				query+="is_Maersk_City='"+maerskCity+"' and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,name,status,is_Maersk_City,port_Flag FROM CITY "+query;
			System.out.println(result_query);
			List<City> cityList = jdbcTemplate.query(result_query, (rs,rowNum)->mapCity(rs));
			for(City city: cityList) {
				cities.add(CityLookup.builder().name(city.getName()).rowid(city.getRowid()).status(city.getStatus())
						.isMaerskCity(city.getIsMaerskCity()).portFlag(city.getPortFlag()).build());
			}
		}
		return cities;
	}
	
	private City mapCity(ResultSet rs) {
		try {
			return City.builder().rowid(rs.getLong("ROWID")).isMaerskCity(rs.getString("IS_MAERSK_CITY")).name(rs.getString("NAME"))
					.portFlag(rs.getString("PORT_FLAG")).status(rs.getString("STATUS")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	@Override
	public List<StateLookup> getState(String name, String status, String workaroundReason){
		List<StateLookup> states = new ArrayList<StateLookup>();
		if(name == null && status == null && workaroundReason == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(status != null) {
				query+="status='"+status+"' and ";
			}
			if(workaroundReason != null) {
				query+="workaround_Reason='"+workaroundReason+"' and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,name,status,valid_From,valid_To FROM STATE "+query;
			List<State> stateList = jdbcTemplate.query(result_query, (rs,rowNum)-> mapState(rs));
			for(State state: stateList) {
				states.add(StateLookup.builder().name(state.getName()).rowid(state.getRowid()).status(state.getStatus())
						.validFrom(state.getValidFrom()).validTo(state.getValidTo()).build());
			}
		}
		return states;
	}
	
	private State mapState(ResultSet rs) {
		try {
			return State.builder().rowid(rs.getLong("ROWID")).name(rs.getString("NAME")).status(rs.getString("STATUS"))
					.validFrom(rs.getString("VALID_FROM")).validTo(rs.getString("VALID_TO")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<CountryLookup> getCountry(String name, String status){
		List<CountryLookup> countries = new ArrayList<CountryLookup>();
		if(name == null && status == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(status != null) {
				query+="status='"+status+"' and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,name,status,valid_From,valid_To FROM COUNTRY "+query;
			List<Country> countryList = jdbcTemplate.query(result_query,(rs,rowNum)-> mapCountry(rs));
			for(Country country: countryList) {
				countries.add(CountryLookup.builder().rowid(country.getRowid()).name(country.getName()).status(country.getStatus())
						.validFrom(country.getValidFrom()).validTo(country.getValidTo()).build());
			}
		}
		return countries;
	}
	
	private Country mapCountry(ResultSet rs) {
		try {
			return Country.builder().rowid(rs.getLong("ROWID")).name(rs.getString("NAME")).status(rs.getString("STATUS"))
					.validFrom(rs.getString("VALID_FROM")).validTo(rs.getString("VALID_TO")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<ContinentLookup> getContinent(String name, String status){
		List<ContinentLookup> continents = new ArrayList<ContinentLookup>();
        if(name == null && status == null) {
			
		}
        else {
        	String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(status != null) {
				query+="status='"+status+"' and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,name,status,valid_From,valid_To FROM CONTINENT "+query;
			List<Continent> continentList = jdbcTemplate.query(result_query, (rs,rowNUm)->mapContinent(rs));
			for(Continent continent: continentList) {
				continents.add(ContinentLookup.builder().name(continent.getName()).rowid(continent.getRowid()).status(continent.getStatus())
						.validFrom(continent.getValidFrom()).validTo(continent.getValidTo()).build());
			}
        }
		return continents;
	}
	
	private Continent mapContinent(ResultSet rs) {
		try {
			return Continent.builder().name(rs.getString("NAME")).rowid(rs.getLong("ROWID")).status(rs.getString("STATUS"))
					.validFrom(rs.getString("VALID_FROM")).validTo(rs.getString("VALID_TO")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<BdaLookup> getBda(String name, String status, Long bdaTypeId){
		List<BdaLookup> bdaLkpList = new ArrayList<BdaLookup>();
		if(name == null && status == null && bdaTypeId == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(status != null) {
				query+="status='"+status+"' and ";
			}
			if(bdaTypeId != null) {
				query+="bda_Type_id ="+bdaTypeId+" and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,name,status,bda_type_id,valid_To FROM BDA "+query;
			List<BDA> bdaList = jdbcTemplate.query(result_query, (rs,rowNum)->mapBDA(rs));
			for(BDA bda: bdaList) {
				bdaLkpList.add(BdaLookup.builder().rowid(bda.getRowid()).name(bda.getName()).status(bda.getStatus())
						.bdaTypeId(Long.valueOf(bda.getValidFrom())).validTo(bda.getValidTo()).build());
			} 
		}
		return bdaLkpList;
	}
	
	private BDA mapBDA(ResultSet rs) {
		try {
			return BDA.builder().name(rs.getString("NAME")).rowid(rs.getLong("ROWID")).status(rs.getString("STATUS"))
					.validFrom(String.valueOf(rs.getLong("BDA_TYPE_ID"))).validTo(rs.getString("VALID_TO")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<BdaTypeLookup> getBdaType(){
		List<BDAType> bdaTypeList = bdaTypeRepo.findAll();
		List<BdaTypeLookup> bdaTypeLkp = new ArrayList<BdaTypeLookup>();
		for(BDAType bdaType: bdaTypeList) {
			bdaTypeLkp.add(BdaTypeLookup.builder().rowid(bdaType.getRowid()).name(bdaType.getName()).build());
		}
		return bdaTypeLkp;
	}
	
	@Override
	public List<FacilityTypeLookup> getFacType(String code, String name, String uniqueValueFlag){
		List<FacilityTypeLookup> facTypeLkp = new ArrayList<FacilityTypeLookup>();
		if(code == null && name == null && uniqueValueFlag == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(code != null) {
				query+="code='"+code+"' and ";
			}
			if(uniqueValueFlag != null) {
				query+="unique_value_flag='"+uniqueValueFlag+"' and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,code,master_type,name FROM FAC_TYPE "+query;
			List<FacilityType> facTypeList = jdbcTemplate.query(result_query, (rs,rowNum)->mapFacType(rs));
			for(FacilityType facType: facTypeList) {
				facTypeLkp.add(FacilityTypeLookup.builder().rowid(facType.getRowid()).code(facType.getCode())
						.masterType(facType.getMasterType()).name(facType.getName()).build());
			}
		}
		return facTypeLkp;
	}
	
	private FacilityType mapFacType(ResultSet rs) {
		try {
			return FacilityType.builder().rowid(rs.getLong("ROWID")).code(rs.getString("CODE")).masterType(rs.getString("MASTER_TYPE"))
					.name(rs.getString("NAME")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<FacilityServiceLookup> getFacilityServices(String name, String code, String isActive, String description, Long serGrpId){
		List<FacilityServiceLookup> facSerLkp = new ArrayList<FacilityServiceLookup>();
		if(name == null && code == null && isActive == null && description == null && serGrpId == null) {
			
		}
		else {
			String query = "where ";
			if(name != null) {
				query+="name like '%"+name+"%' and ";
			}
			if(code != null) {
				query+="vas_codes='"+code+"' and ";
			}
			if(isActive != null) {
				query+="is_active='"+isActive+"' and ";
			}
			if(description != null) {
				query+="description like '%"+description+"%' and ";
			}
			if(serGrpId != null) {
				query+="service_grp_id="+serGrpId+" and ";
			}
			query = query.substring(0, query.length()-4);
			String result_query = "SELECT rowid,description,is_active,name,vas_codes FROM FAC_SERVICE "+query;
			List<FacilityService> facSerList = jdbcTemplate.query(result_query, (rs,rowNum)->mapFacilityService(rs));
			for(FacilityService facSer: facSerList) {
				facSerLkp.add(FacilityServiceLookup.builder().code(facSer.getVasCodes()).description(facSer.getDescription()).isActive(facSer.getIsActive())
				.name(facSer.getName()).rowid(facSer.getRowid()).build());
			}
		}
		return facSerLkp;
	}
	
	private FacilityService mapFacilityService(ResultSet rs) {
		try {
			return FacilityService.builder().description(rs.getString("DESCRIPTION")).isActive(rs.getString("IS_ACTIVE")).name(rs.getString("NAME"))
					.rowid(rs.getLong("ROWID")).vasCodes(rs.getString("VAS_CODES")).build();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<CodeTypeLookup> getTypeCodes(){
		List<CodeType> codeTypeList = codeTypeRepo.findAll();
		List<CodeTypeLookup> codeTypeLkp = new ArrayList<CodeTypeLookup>();
		if(codeTypeList != null) {
			for(CodeType codeType : codeTypeList) {
				codeTypeLkp.add(CodeTypeLookup.builder().rowid(codeType.getRowid()).name(codeType.getTypeName()).code(codeType.getTypeCode()).build());
			}
		}
		return codeTypeLkp;
	}
}
