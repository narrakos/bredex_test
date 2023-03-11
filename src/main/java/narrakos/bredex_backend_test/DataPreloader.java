package narrakos.bredex_backend_test;

import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.AdRepository;
import narrakos.bredex_backend_test.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DataPreloader implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public DataPreloader(UserRepository userRepository, AdRepository adRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadData();
    }

    private void loadData() {
        List<User> users = loadUsers();
        loadAds(users);
    }

    private List<User> loadUsers() {
        List<User> users = generateUsers();
        userRepository.saveAll(users);
        return users;
    }

    private List<User> generateUsers() {
        return List.of(
                new User("Teszt Tamás", "teszt.tamas0@gmail.com"),
                new User("Barbabas Bogue", "bbogue1@purevolume.com"),
                new User("Flor Maeer", "fmaeer2@hatena.ne.jp"),
                new User("Emelita Wissbey", "ewissbey3@freewebs.com"),
                new User("Brent Sturge", "bsturge4@ucoz.com"),
                new User("Lazaro Rhule", "lrhule5@nyu.edu"),
                new User("Maximilien Lomax", "mlomax6@about.me"),
                new User("Dalis Broschke", "dbroschke7@live.com"),
                new User("Joanna Moyer", "jmoyer8@bloglovin.com"),
                new User("Alysia Pettisall", "apettisall9@photobucket.com"),
                new User("Mignonne Housby", "mhousbya@plala.or.jp"),
                new User("Mendy Guarin", "mguarinb@etsy.com"),
                new User("Marcello Dobsons", "mdobsonsc@blogs.com"),
                new User("Gretna Osselton", "gosseltond@about.com"),
                new User("Kaleb Mitchelson", "kmitchelsone@narod.ru"),
                new User("Nerissa Warby", "nwarbyf@usatoday.com"),
                new User("Mathian Guislin", "mguisling@over-blog.com"),
                new User("Sharai McLemon", "smclemonh@163.com"),
                new User("Joey Overington", "joveringtoni@vinaora.com"),
                new User("Candice Shillan", "cshillanj@ustream.tv"),
                new User("Carly Vaune", "cvaunek@yolasite.com"),
                new User("Roger Salvage", "rsalvagel@google.com.au"),
                new User("Grier Quinnelly", "gquinnellym@cmu.edu"),
                new User("Farah Lydden", "flyddenn@nydailynews.com"),
                new User("Franz Gamon", "fgamono@topsy.com"),
                new User("Gloriana Falcus", "gfalcusp@angelfire.com"),
                new User("Lyell Beevors", "lbeevorsq@vimeo.com"),
                new User("Maddie Keirle", "mkeirler@oaic.gov.au"),
                new User("Ranique Marusik", "rmarusiks@moonfruit.com"),
                new User("Wit Pakenham", "wpakenhamt@youku.com"),
                new User("Wayland Collibear", "wcollibearu@toplist.cz"),
                new User("Steffane Blasgen", "sblasgenv@cnbc.com"),
                new User("Alyosha Saye", "asayew@umich.edu"),
                new User("Deeanne Highway", "dhighwayx@forbes.com"),
                new User("Horst Selly", "hsellyy@yahoo.co.jp"),
                new User("Liane Polglaze", "lpolglazez@deliciousdays.com"),
                new User("Florenza Hamil", "fhamil10@acquirethisname.com"),
                new User("Rene Dobrowolski", "rdobrowolski11@sphinn.com"),
                new User("Salem Kerman", "skerman12@marriott.com"),
                new User("Astrix Reedman", "areedman13@cnn.com"),
                new User("Griffin Eyeington", "geyeington14@dyndns.org"),
                new User("Teddy Daens", "tdaens15@adobe.com"),
                new User("Neala Folkerd", "nfolkerd16@homestead.com"),
                new User("Nelle Challis", "nchallis17@ameblo.jp"),
                new User("Nolie Stonehewer", "nstonehewer18@omniture.com"),
                new User("Jamima Schollar", "jschollar19@uiuc.edu"),
                new User("Carolina Durden", "cdurden1a@washingtonpost.com"),
                new User("Saul Doick", "sdoick1b@bing.com"),
                new User("Waldo Kelleher", "wkelleher1c@delicious.com"),
                new User("Hasty Gloucester", "hgloucester1d@phpbb.com")
        );
    }

    private void loadAds(List<User> users) {
        List<Ad> ads = generateAds();
        final Random random = new Random();
        ads.forEach(a -> a.setUser(users.get(random.nextInt(49) + 1)));
        setFixAds(ads, users);
        adRepository.saveAll(ads);
    }

    private void setFixAds(List<Ad> ads, List<User> users) {
        User user = users.get(0);
        ads.get(0).setUser(user);
        ads.get(1).setUser(user);
        ads.get(2).setUser(user);
        ads.get(3).setUser(user);
    }

    private List<Ad> generateAds() {
        return List.of(
                new Ad("Suzuki", "Swift", "Első teszt", 6649786696L),
                new Ad("BMW", "M5", "Második teszt", 3807874359L),
                new Ad("Mercedes-Benz", "SLK-Class", "Harmadik teszt", 8676965581L),
                new Ad("Toyota", "Corolla", "Negyedik teszt", 8401253907L),
                new Ad("Chevrolet", "Suburban 1500", "at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue", 3077755964L),
                new Ad("Subaru", "Forester", "mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at", 7205822074L),
                new Ad("Ford", "E150", "nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac", 47838769L),
                new Ad("Volvo", "C30", "consequat ut nulla sed accumsan felis ut at dolor quis", 9861944298L),
                new Ad("Volvo", "S90", "quis libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a", 2311188124L),
                new Ad("Jaguar", "XF", "augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo", 2260440911L),
                new Ad("Buick", "Regal", "aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci", 1545690245L),
                new Ad("Ford", "Thunderbird", "eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum", 2013883499L),
                new Ad("Morgan", "Aero 8", "sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis", 4925079832L),
                new Ad("Porsche", "911", "lectus in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa", 2426875763L),
                new Ad("Cadillac", "Eldorado", "cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra", 9172502755L),
                new Ad("Chrysler", "PT Cruiser", "rutrum nulla nunc purus phasellus in felis donec semper sapien a libero nam dui proin leo odio porttitor id", 3365785293L),
                new Ad("Volkswagen", "Golf", "urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec", 2761705307L),
                new Ad("Kia", "Rio", "maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis", 7634150314L),
                new Ad("Volkswagen", "GTI", "leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus", 1612748072L),
                new Ad("Lotus", "Esprit", "morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel", 7443640661L),
                new Ad("Lincoln", "LS", "arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in", 4952460793L),
                new Ad("Maybach", "62", "ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi", 6659881605L),
                new Ad("Suzuki", "Esteem", "neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi", 7860385500L),
                new Ad("Chrysler", "New Yorker", "ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit", 8183699443L),
                new Ad("Chevrolet", "Cruze", "penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis", 7286796310L),
                new Ad("Mercedes-Benz", "CLK-Class", "cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam", 9826348437L),
                new Ad("Mercury", "Sable", "a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis", 8691571353L),
                new Ad("Oldsmobile", "98", "vitae nisi nam ultrices libero non mattis pulvinar nulla pede ullamcorper augue a suscipit", 2234161759L),
                new Ad("Hyundai", "Accent", "odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet", 1200863752L),
                new Ad("Honda", "Civic", "hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at", 3707326653L),
                new Ad("Audi", "Q5", "consequat morbi a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla", 6017697209L),
                new Ad("Suzuki", "Swift", "proin eu mi nulla ac enim in tempor turpis nec euismod", 1413177622L),
                new Ad("Dodge", "Viper", "euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec", 7369273132L),
                new Ad("Plymouth", "Roadrunner", "pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam", 7276128888L),
                new Ad("Dodge", "Intrepid", "risus praesent lectus vestibulum quam sapien varius ut blandit non interdum in ante vestibulum ante ipsum primis in faucibus orci", 5694574418L),
                new Ad("Cadillac", "DeVille", "amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla", 8306662323L),
                new Ad("Buick", "Lucerne", "cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis", 3155264751L),
                new Ad("Volvo", "S40", "luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium", 4417068987L),
                new Ad("Chevrolet", "Camaro", "vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum", 4782459945L),
                new Ad("Volkswagen", "GTI", "odio condimentum id luctus nec molestie sed justo pellentesque viverra pede", 3187461259L),
                new Ad("Pontiac", "Bonneville", "ornare consequat lectus in est risus auctor sed tristique in tempus", 4598809542L),
                new Ad("Volkswagen", "Eurovan", "odio curabitur convallis duis consequat dui nec nisi volutpat eleifend donec ut dolor morbi vel lectus in quam fringilla", 5953457648L),
                new Ad("Mercedes-Benz", "300SL", "vestibulum quam sapien varius ut blandit non interdum in ante vestibulum ante ipsum primis in", 1034625222L),
                new Ad("Chevrolet", "2500", "erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea", 4240372346L),
                new Ad("Pontiac", "Grand Am", "elit ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit", 5081672789L),
                new Ad("Audi", "A8", "morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor", 6827308295L),
                new Ad("Pontiac", "Grand Prix", "sit amet justo morbi ut odio cras mi pede malesuada in imperdiet", 4207522600L),
                new Ad("Subaru", "Forester", "integer pede justo lacinia eget tincidunt eget tempus vel pede morbi porttitor lorem id", 186491078L),
                new Ad("Isuzu", "Rodeo Sport", "volutpat dui maecenas tristique est et tempus semper est quam", 6262703828L),
                new Ad("Ford", "F-Series", "sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam", 7848291922L),
                new Ad("Ford", "F-Series", "diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante", 7839499212L),
                new Ad("GMC", "Savana 3500", "nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non", 9127227432L),
                new Ad("Volvo", "V90", "faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat", 6731440558L),
                new Ad("Maybach", "62", "odio justo sollicitudin ut suscipit a feugiat et eros vestibulum", 5372426904L),
                new Ad("Subaru", "Forester", "nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in", 3196846730L),
                new Ad("Ford", "Ranger", "blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing", 4163278034L),
                new Ad("Audi", "Allroad", "aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio", 6507214371L),
                new Ad("Dodge", "Nitro", "pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis", 212772564L),
                new Ad("Subaru", "SVX", "massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut", 9625877879L),
                new Ad("Cadillac", "Escalade ESV", "felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque", 7721790609L),
                new Ad("Chevrolet", "G-Series G10", "maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin", 5549501323L),
                new Ad("Renault", "Alliance", "at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna", 8066969838L),
                new Ad("Lexus", "RX", "pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus", 3372104855L),
                new Ad("Mercedes-Benz", "M-Class", "justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus", 7941349608L),
                new Ad("GMC", "Sierra 1500", "in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat", 4751173280L),
                new Ad("Ford", "LTD Crown Victoria", "euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam", 4600660763L),
                new Ad("Ford", "Expedition", "sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem", 3476348L),
                new Ad("Chevrolet", "Camaro", "venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit eu est", 3322233083L),
                new Ad("Buick", "Terraza", "ut nulla sed accumsan felis ut at dolor quis odio", 8449727505L),
                new Ad("Lexus", "RX Hybrid", "neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut", 9185467006L),
                new Ad("Isuzu", "Amigo", "morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede", 7975661807L),
                new Ad("Nissan", "Frontier", "non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac", 7326028551L),
                new Ad("BMW", "X5 M", "aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras", 2322674109L),
                new Ad("Mercedes-Benz", "300E", "integer ac leo pellentesque ultrices mattis odio donec vitae nisi nam ultrices libero non mattis", 3688990486L),
                new Ad("Bentley", "Continental", "est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante", 2599116383L),
                new Ad("Chevrolet", "Equinox", "est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in", 9404080055L),
                new Ad("Saturn", "L-Series", "nunc viverra dapibus nulla suscipit ligula in lacus curabitur at ipsum ac tellus semper interdum mauris ullamcorper purus sit amet", 6469621935L),
                new Ad("GMC", "Sierra 1500", "ligula in lacus curabitur at ipsum ac tellus semper interdum mauris ullamcorper purus sit", 1617097333L),
                new Ad("Dodge", "Stratus", "felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis", 9891034390L),
                new Ad("Volkswagen", "Rabbit", "sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan", 2588176286L),
                new Ad("Toyota", "Tercel", "quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio", 7209889228L),
                new Ad("Acura", "Integra", "ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus", 9516452712L),
                new Ad("Toyota", "Matrix", "turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam", 3111396678L),
                new Ad("Mercedes-Benz", "E-Class", "est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in", 3780364404L),
                new Ad("Mazda", "929", "nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin", 4177500384L),
                new Ad("Mercedes-Benz", "R-Class", "aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis", 3189228340L),
                new Ad("Suzuki", "Vitara", "suscipit nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus", 8576766344L),
                new Ad("Buick", "Coachbuilder", "eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum", 4264908878L),
                new Ad("Chrysler", "Pacifica", "at dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis odio", 2083542882L),
                new Ad("Cadillac", "DTS", "sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem", 4700643611L),
                new Ad("Kia", "Carens", "ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros", 2380246678L),
                new Ad("Chevrolet", "Tahoe", "libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a", 7522043737L),
                new Ad("Ford", "Mustang", "primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra", 6733103921L),
                new Ad("Toyota", "Celica", "in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum", 1424315890L),
                new Ad("Chevrolet", "Colorado", "rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis", 1785938891L),
                new Ad("Volkswagen", "Phaeton", "id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui", 5414076095L),
                new Ad("Chevrolet", "Tahoe", "luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse", 2397449017L),
                new Ad("Chevrolet", "Express 3500", "lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a", 4482790517L),
                new Ad("GMC", "Suburban 2500", "ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor", 7030517853L),
                new Ad("Subaru", "SVX", "justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie", 2632276096L)
        );
    }
}
