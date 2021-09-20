package fr.groupe1.goevent;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import fr.groupe1.goevent.controllers.OrganisateurController;
import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.Prestation;
import fr.groupe1.goevent.entities.Role;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IAdressRepository;
import fr.groupe1.goevent.repository.IEventRepository;
import fr.groupe1.goevent.repository.IPrestationRepository;
import fr.groupe1.goevent.repository.IRoleRepository;
import fr.groupe1.goevent.repository.IUserRepository;
import fr.groupe1.goevent.service.IUserService;

@SpringBootApplication
@ComponentScan({"fr.groupe1.goevent"})
public class GoeventApplication {
	
	
	@Autowired
	static IUserService userService;
	
	@Autowired
	static IUserRepository userRepository;
	
	@Autowired
	static IEventRepository eventRepository;
	
	@Autowired
	static IAdressRepository addressRepository;
	
	@Autowired
	static IPrestationRepository prestationRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
		new File(OrganisateurController.uploadDirectory).mkdir();
		SpringApplication.run(GoeventApplication.class, args);
	}
	
	public static String EMAIL_SESSION_USER = "richiez@gmail.com";

	
	static ZoneId defaultZoneId = ZoneId.systemDefault();
	
	
	// Populate Dates

    static  LocalDate localDate20210819 = LocalDate.of(2021, 8, 19);
    static  Date date20210819 = Date.from(localDate20210819.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20200819 = LocalDate.of(2020, 8, 19);
    static  Date date20200819 = Date.from(localDate20200819.atStartOfDay(defaultZoneId).toInstant());
      
    static  LocalDate localDate20200825 = LocalDate.of(2020, 8, 25);
    static  Date date20200825 = Date.from(localDate20200825.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20210825 = LocalDate.of(2021, 8, 25);
    static  Date date20210825 = Date.from(localDate20210825.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20220819 = LocalDate.of(2022, 8, 19);
    static  Date date20220819 = Date.from(localDate20220819.atStartOfDay(defaultZoneId).toInstant());
      
    static  LocalDate localDate20220825 = LocalDate.of(2022, 8, 25);
    static  Date date20220825 = Date.from(localDate20220819.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20180825 = LocalDate.of(2018, 8, 25);
    static  Date date20180825 = Date.from(localDate20180825.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20160825 = LocalDate.of(2016, 8, 25);
    static  Date date20160825 = Date.from(localDate20160825.atStartOfDay(defaultZoneId).toInstant());
    
    static  LocalDate localDate20160829 = LocalDate.of(2018, 8, 29);
    static  Date date20160829 = Date.from(localDate20160825.atStartOfDay(defaultZoneId).toInstant());

  //  String password = bCryptPasswordEncoder.encode("password");
	
    public final static String ADMIN = "Admin";
    public final static String PARTICIPANT = "Participant";
    public final static String ORGANIZER = "Organisateur";
    public final static String PROVIDER = "Fournisseur";

    
    static List<Role> roles = Arrays.asList(
    		new Role(1l, ADMIN),
    		new Role(2l, PARTICIPANT),
    		new Role(3l, ORGANIZER),
    		new Role(4l, PROVIDER)
    		
    		);
    
    static List<Role> roleAdmin =  Arrays.asList(roles.get(0));
    static List<Role> roleParticipant =  Arrays.asList(roles.get(1));
    static List<Role> roleOrganizer =  Arrays.asList(roles.get(2));
    static List<Role> roleProvider =  Arrays.asList(roles.get(3));
	
	static List<User> users = Arrays.asList(
			new User(1,"Nicolas","Brosse","brosse@gmail.com","password","0673994431", date20210819,"test",roleProvider),
		    new User(2,"Geoffroy","Richez","participantgoevent@gmail.com","password","0641227310", date20210819,"test", roleParticipant),
		    new User(3,"Jean","Dubois","dubois@gmail.com","password","0627231630", date20210819,"test",roleParticipant),
		    new User(4,"Marc","Mandalinic","mandalinic@gmail.com","password","0620160209", date20210819,"test", roleOrganizer),
		    new User(5,"Mei","Benoit","benoit@gmail.com","password","0622125740", date20210819,"test",roleParticipant),
		    new User(6,"Fitriana","Manik","manik@gmail.com","password","085862012", date20210819,"test", roleAdmin),	    
		    new User(7,"Gabriel","Petit","petit@gmail.com","password","0622125740", date20210819,"test", roleParticipant ),
		    new User(8,"Leo","Dubois","mandeline@gmail.com","password","0620160209", date20210819,"test",roleParticipant),
		    new User(9,"George","Abitbol","abitbol@gmail.com","password","0637919972", date20210819,"test",roleParticipant),
		    new User(10,"Alice","Mercier","mercier@gmail.com","password","0622125740", date20210819,"test",roleParticipant),
		    new User(11,"Jules","Berger","berger@gmail.com","password","0652886353", date20210819,"test",roleParticipant),
		    new User(12,"Dark","Vador","vador@gmail.com","password","0637919972", date20210819,"test",roleParticipant),
		    new User(13,"Jules","Berger","berger@gmail.com","password","0641227310", date20210819,"test",roleParticipant),
		    new User(14,"Marty","McFly","mcfly@gmail.com","password","0652886353", date20210819,"test",roleParticipant),
		    new User(15,"Juliette","Perret","perret@gmail.com","password","0641227310", date20210819,"test",roleParticipant),
		    new User(16,"John","Doe","doe@gmail.com","password","0652886353", date20210819,"test",roleParticipant),
		    new User(17,"Gabin","Leclerc","leclerc@gmail.com","password","0652886353", date20210819,"test",roleParticipant),
		    new User(18,"Juste","Leblanc","leblanc@gmail.com","password","0641227310", date20210819,"test",roleParticipant),
		    new User(19,"Justin","Bridoux","bridou@gmail.com","password","0637919972", date20210819,"test",roleParticipant),
		    new User(20,"Louis","Guillot","guillot@gmail.com","password","0630486185", date20210819,"test",roleParticipant)
		    
		 );
	
	static List<Adress> addresss = Arrays.asList(
    		new Adress(1, "Paris", "Champs Elysee", 75000, 2.349014, 48.864716),
    		new Adress(2, "Marseille", "Cannebiere", 13000, 5.38107, 43.29695),
    		new Adress(3, "Roanne", "Le port", 42300, 4.0729178, 46.0345572),
    		new Adress(4, "Lyon", "place Bellecour", 69000, 4.832026481628418, 45.75755310058594),
    		new Adress(5, "Grenoble", "Place Verdun", 38000, 5.732227325439453, 45.188819885253906),
    		new Adress(6, "Clamart", "Centre Ville", 13000, 2.2630292, 48.800368),
    		new Adress(7, "Poitiers", "Futuroscope", 67000, 0.3670214, 46.6674543),
    		new Adress(8, "Aubagne", "Place De Gaulle", 13000, 5.5703031, 43.2924385),
    		new Adress(9, "Paris", "Montrouge", 75000, 2.3429494, 48.8530103),
    		new Adress(10, "Paris", "Barbès", 75000, 2.3493707, 48.8839388),
    		new Adress(11, "Monpellier", "Place de la Comédie", 34000, 3.8798508644104004,43.60835647583008),
    		new Adress(12, "Toulouse", "Prairie des filtres", 31000, 1.4369276762008667, 43.59547805786133),
    		new Adress(13, "Valence", "Grand Boulevard", 26000, 1.4369276762008667, 43.59547805786133),
    		new Adress(14, "Saint Etienne", "Stade Geoffroy Guicghard", 42000, 4.390172481536865, 45.460758209228516)
    	);
	
	
	static List<EventType> eventTypes = Arrays.asList(
    		new EventType(1, "Salon", "Ceci est un salon"),
    		new EventType(2, "Conférence", "Ceci est une conférence")
    	);
	
	static List<Event> events = Arrays.asList(
    		new Event(1, "Salon du livre", eventTypes.get(0), "Tout ce que vous pouvez lire...", date20210819 , date20210825, 6000, addresss.get(0), "Livres.jpg", "Animé par la volonté de développer le goût du livre et de la lecture sous toutes ses formes et pour tous les publics, le salon du livre a connu ces dernières années un large développement. C’est ainsi que le week-end du salon, nous offrons aux habitants de Paris et de sa région, non seulement des rencontres avec des auteurs, mais aussi tout un ensemble d’animations et d’évènements.", 15.00),
    		new Event(2, "Salon des fleurs", eventTypes.get(0), "Sauvegarde & Préservation", date20220819 , date20220825, 4000,  addresss.get(1), "Fleur.jpg","Le salon des fleurs de Strasbourg revient pour une nouvelle édition en 2021. Retrouver les grands classiques et les dernières tendances florales, en plus de pouvoir découvrir et acheter les compositions florales de nos exposants vous pourrez participer à des ateliers pour apprendre à faire vos propres bouquets.", 16.00),
    		new Event(3, "Congrès Faune Sauvage", eventTypes.get(1), "Le pouvoir des fleurs", date20220819 , date20220825, 500,  addresss.get(2), "lion.jpg", "Ce Congrès s’impose d’années en années comme un événement incontournable. L’objectif de cette journée est de réunir toutes les personnes intéressées par la sauvegarde des espèces en voie de disparition. Le nombre particulièrement élevé d’animaux dont la protection en vertu de la Convention est proposée est un signe inquiétant du déclin systématique des espèces sauvages dans toutes les régions du monde. Des mesures urgentes sont nécessaires pour assurer la survie de ces espèces.", 55.50),
    		new Event(4, "Salon du Vélo", eventTypes.get(0), "Tous en selle !", date20220819 , date20220825, 5000,  addresss.get(3), "Velo.jpg","Cet événement regroupe tous les types de bicyclettes, des mountains bikes aux vélos de ville, en passant par les vélos de course, de trekking ou encore de promenade, ainsi qu'un grand nombre d'accessoires. Les cyclistes, débutants ou aguerris, trouveront lors du salon les dernières technologies, idées et activités et passeront un bon moment.", 10.00),
    		new Event(5, "Salon de la Pâtisserie", eventTypes.get(0), "IMac à Rond", date20220819 , date20220825, 2500,  addresss.get(4), "patisserie.jpg","Le Salon de la Pâtisserie a pour objectif de mettre en lumière les talents de nos artisans et les saveurs de nos terroirs. C’est l’occasion de faire communier amateurs et professionnels, de promouvoir le partage des savoirs et connaissances nouvelles. Regarder, écouter, pâtisser mais aussi déguster dans un cadre convivial et riche d’enseignements.", 5.20),
    		new Event(6, "Congrès Informatique", eventTypes.get(1),"Git Lab", date20220819 , date20220825, 300,  addresss.get(5), "Informatique.jpg", "Le Congrès de la Cybersécurité s'est imposé comme l'événement de référence en Europe en matière de sécurité et de confiance numérique. Son originalité est de favoriser la réflexion et l'échange au sein de l'écosystème européen de la cybersécurité. De plus, il facilite les rencontres entre acheteurs et fournisseurs de solutions de cybersécurité.", 85.00),
    		new Event(7, "Congrès d'Astronomie", eventTypes.get(1), "Étoile & Lumière", date20220819 , date20220825, 400,  addresss.get(6), "Etoile.jpg", "Un événement d’une rare intensité qui propose 150 conférences, table-rondes, forums ou ateliers pour satisfaire la curiosité d’un large public, qui donne à découvrir la plus grande galerie marchande consacrée à l'astronomie, et valorise par des expositions, des présentations, la contribution des acteurs scientifiques et industriels dans le développement de nos connaissances.", 75.00),
    		new Event(8, "Salon de l'Agriculture", eventTypes.get(0), "Biologique", date20220819 , date20220825, 10000,  addresss.get(7), "Agriculture.jpg","Le salon de l'agriculture rassemble quatre univers : élevage et ses filières (plus de 300 animaux), produits gastronomiques (avec de nombreuses animations culinaires, culture et filières végétales (découvrez les nouvelles tendances autour d'animations), services et métiers de l'agriculture. Au programme : des concours participatifs, des découvertes inédites de spécialités issues de terroirs français, mais aussi de nombreuses surprises et animations, ainsi que le célèbre concours Lépine.", 20.00),
    		new Event(9, "Salon du Vin", eventTypes.get(0), "Rouge & blanc", date20220819 , date20220825, 7000,  addresss.get(8), "Vin.jpg", "Salons de vignerons, dégustations géantes, festivals en tous genres : l’occasion vous est donnée, tout au long de l’année, de partir à la découverte de cuvées inconnues, de rencontrer des producteurs issus de tous les terroirs du pays et de l'étranger, et surtout, de partager cette passion du vin avec les amateurs éclairés et amoureux de la vigne.", 20.00),
    		new Event(10, "Salon de la Gastronomie", eventTypes.get(0), "Légumes de saison", date20220819 , date20220825, 8500,  addresss.get(9), "Gastronomie.jpg", "Le Salon de la Gastronomie de Roanne accueille une centaine de stands de la gastronomie avec des produits de qualité qui représentent toutes les régions de France (vins, alimentation, produits gastronomiques, art de la table) et propose des démonstrations culinaires.", 20.00),
    		new Event(11, "Salon des Légumes", eventTypes.get(0), "Légumes anciens", date20220819 , date20220825, 8500,  addresss.get(10), "Legumes.jpg", "1er marché international des Légumes, Montpellier qui organise depuis 20 ans le Forum international des Légumes, est le lieu stratégique pour accueillir cette nouvelle plateforme de rencontres. 300 opérateurs du marché (production, négoce, commerce, import-export, expédition...) y exposeront leurs produits et innovations, et 50 fournisseurs de transport et logistique du froid et du frais proposeront leurs services.", 20.00),
    		new Event(12, "Salon des Fruits", eventTypes.get(0), "Fruits exotiques", date20220819 , date20220825, 8500,  addresss.get(11), "fruits.jpg","1er marché international des Fruits, Montpellier qui organise depuis 20 ans le Forum international des Légumes, est le lieu stratégique pour accueillir cette nouvelle plateforme de rencontres. 300 opérateurs du marché (production, négoce, commerce, import-export, expédition...) y exposeront leurs produits et innovations, et 50 fournisseurs de transport et logistique du froid et du frais proposeront leurs services.", 20.00),
    		new Event(13, "Salon de l'Automobile", eventTypes.get(0), "Ancienne et de Collection", date20220819 , date20220825, 6000,  addresss.get(12), "Automobile.jpg","Le Salon de l’automobile revient au Parc Expo de Toulouse avec une nouvelle identité : Tours Auto & Mobile. Tours Auto & Mobile sera un événement éco-responsable et s’engage pour relever le défi environnemental et imagine son rendez-vous 2021 comme respectueux du développement durable. 4 jours pour fêter l’automobile autour de nombreuses animations, un Village de l’Innovation et un fil rouge : l’écomobilité.", 20.00),
    		new Event(14, "Salon de la Piscine", eventTypes.get(0), "Bleu & Blanc", date20220819 , date20220825, 8500,  addresss.get(13), "Piscine2.jpg", "Salon Piscine est dédié à un an dans le Parc Expo St Etienne, avec piscines, un sauna et un spa. Parmi les sociétés exposantes fournisseurs de piscines, saunas, produits sanitaires, piscines, équipements de chauffage et de purification d'eau, maillot de bain, appareils de musculation et bien d'autres peuvent être trouvés. Les visiteurs du Salon de la Piscine & du Spa vous pouvez recueillir toutes les informations nécessaires à la construction et l'exploitation de piscines, etc, et demander l'avis de première main.", 20.00),
    		new Event(15, "Salon du Chocolat", eventTypes.get(0), "Noir & Blanc", date20220819 , date20220825, 8500,  addresss.get(0), "Chocolat.jpg", 20.00),
    		new Event(16, "Salon de la Course à pied", eventTypes.get(0), "Le lièvre et la tortue", date20220819 , date20220825, 8500,  addresss.get(1), "CourseAPied.jpg", 20.00),
    		new Event(17, "Salon du Crossfit", eventTypes.get(0), "Se dépasser", date20160825 , date20160825, 8500,  addresss.get(2), "Crossfit.jpg", 20.00),
    		new Event(18, "Salon du Mariage", eventTypes.get(0), "Coloré", date20160825 , date20160825, 8500,  addresss.get(3), "Mariage.jpg", 20.00),
    		new Event(19, "Congrès des Matériaux", eventTypes.get(1), "Acier", date20160825 , date20160829, 8500,  addresss.get(4), "Materiaux.jpg", "Le Congrès européen sur les matériaux avancés et leurs procédés a pour objectif de favoriser le transfert de connaissances et l'échange d'expériences entre les professionnels issus des milieux académiques et industriels. Plusieurs grands domaines seront traités : matériaux fonctionnels, matériaux de structure, caractérisation et modélisation, éducation, stratégie et transfert de technologie et matériaux pour la circularité et la durabilité.", 20.00),
    		new Event(20, "Salon de la Voile", eventTypes.get(1), "Porté par le vent", date20180825 , date20180825, 8500,  addresss.get(5), "boat.jpg","Bateaux à voile, à moteur, sports de glisse, équipements, nouvelles technologies, services de la location, du tourisme mais aussi de la pêche, sans oublier, de manière plus générale, tout l'environnement des loisirs nautiques seront exposés sur le salon. Un large choix qui permet à chacun de comparer les produits, de bénéficier de conseils de professionnels et de profiter d'une ambiance conviviale sur les différents espaces d'animations.", 20.00),
    		new Event(21, "Salon du livre 2020", eventTypes.get(0), "Tout ce que vous pouvez lire...", date20200819 , date20200825, 6000, addresss.get(0), "Livres.jpg", 15.00),
    		new Event(22, "Salon des fleurs 2020", eventTypes.get(0), "Sauvegarde & Préservation", date20200819 , date20200825, 4000,  addresss.get(1), "Fleur.jpg","Le salon des fleurs revient pour une nouvelle édition en 2021. Retrouver les grands classiques et les dernières tendances florales, en plus de pouvoir découvrir et acheter les compositions florales de nos exposants vous pourrez participer à des ateliers pour apprendre à faire vos propres bouquets.", 16.00),
    		new Event(23, "Congrès Faune Sauvage 2020", eventTypes.get(1), "Le pouvoir des fleurs", date20200819 , date20200825, 500,  addresss.get(2), "lion.jpg", 55.50)
    	);
	
	static List<Prestation> prestations = Arrays.asList(
    		new Prestation(1l, "Repas italien", 10, null, users.get(0)),
    		new Prestation(2l, "Repas Burger", 12, null, users.get(0)),
    		new Prestation(3l, "Hotêl", 100, null, users.get(4)),
    		new Prestation(4l, "Animation", 20, null, users.get(4))
    	);
	

	@Bean
	public static ApplicationRunner initializerUser(IUserService userService) {
	    return args -> userService.saveAllUsers(users);
	}
	
	@Bean
	public static ApplicationRunner initializerRole(IRoleRepository repository) {
		
	    return args -> repository.saveAll(roles);
	}
	
	@Bean
	public static ApplicationRunner initializerPrestation(IPrestationRepository repository) {
	    return args -> repository.saveAll(prestations);
	}
	

	@Bean
	public static ApplicationRunner initializerEvent(IEventRepository repository) {
		events.get(1).setParticipants(users);
		events.get(1).setPrestations(prestations);
		
		
		events.get(0).getParticipants().add(users.get(1));
		events.get(2).getParticipants().add(users.get(1));
		events.get(3).getParticipants().add(users.get(1));
		events.get(4).getParticipants().add(users.get(1));
		events.get(19).getParticipants().add(users.get(1));
		events.get(18).getParticipants().add(users.get(1));
		
		
		events.get(0).setOrganizer(users.get(3));
		events.get(1).setOrganizer(users.get(3));
		events.get(2).setOrganizer(users.get(3));
		events.get(3).setOrganizer(users.get(3));
		events.get(4).setOrganizer(users.get(6));
		events.get(5).setOrganizer(users.get(6));
		events.get(16).setOrganizer(users.get(3));
		events.get(17).setOrganizer(users.get(3));
		
		
		
	    return args -> repository.saveAll(events);
	}
	

}
