package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.*;

import java.util.ArrayList;

public class RepositoriesTest {
    static void main() {
        InMemoryUserRepository users = new InMemoryUserRepository(new ArrayList<>());
        users.add(new IndividualUser("jan.kowalski@example.com", "Jan Kowalski", "85031212345"));
        users.add(new IndividualUser("anna.nowak@example.com", "Anna Nowak", "92052023456"));
        users.add(new IndividualUser("piotr.wisniewski@example.com", "Piotr Wiśniewski", "78110334567"));
        users.add(new IndividualUser("magdalena.wojcik@example.com", "Magdalena Wójcik", "95081545678"));
        users.add(new IndividualUser("tomasz.kaminski@example.com", "Tomasz Kamiński", "88012856789"));
        users.add(new IndividualUser("katarzyna.lewandowska@example.com", "Katarzyna Lewandowska", "90040567890"));
        users.add(new IndividualUser("michal.zielinski@example.com", "Michał Zieliński", "83091978901"));
        users.add(new IndividualUser("agnieszka.szymanska@example.com", "Agnieszka Szymańska", "97120189012"));
        users.add(new IndividualUser("pawel.wozniak@example.com", "Paweł Woźniak", "76061490123"));
        users.add(new IndividualUser("ewelin.kozlowska@example.com", "Ewelina Kozłowska", "91022801234"));;
        users.add(new CompanyUser("biuro@techcraft.pl", "TechCraft Sp. z o.o.", "729-123-45-11"));
        users.add(new CompanyUser("kontakt@novasoft.pl", "NovaSoft S.A.", "526-987-65-43"));
        users.add(new CompanyUser("office@polbud.pl", "PolBud Usługi Budowlane", "894-321-09-87"));
        users.add(new CompanyUser("zamowienia@logix.pl", "Logix Logistyka Sp. k.", "951-753-15-93"));
        users.add(new CompanyUser("kontakt@finedesign.pl", "Fine Design Studio", "678-246-80-13"));
        users.add(new CompanyUser("biuro@ecoenergy.pl", "EcoEnergy Solutions", "123-456-78-90"));
        users.add(new CompanyUser("rekrutacja@datahub.pl", "DataHub Technologies", "852-147-96-30"));
        users.add(new CompanyUser("faktury@apexmetal.pl", "Apex Metal Sp. z o.o.", "369-258-14-70"));
        users.add(new CompanyUser("info@greenfood.pl", "GreenFood Polska", "741-852-96-30"));
        users.add(new CompanyUser("kontakt@cloudline.pl", "CloudLine Systems", "963-852-74-10"));

        System.out.println("users.findByEmail(\"x@x.pl\") = " + users.findByEmail("x@x.pl"));
        System.out.println("users.findByEmail(\"anna.nowak@example.com\") = " + users.findByEmail("anna.nowak@example.com"));
        System.out.println("users.findAll() = " + users.findAll());
        System.out.println("users.findAll().size() = " + users.findAll().size());

        InMemoryResourceRepository resources = new InMemoryResourceRepository(new ArrayList<>());

        resources.add(new Room("Sali konferencyjna Alpha", 10, 80.00));
        resources.add(new Room("Gabinet spotkań 1on1", 2, 35.00));
        resources.add(new Room("Sala szkoleniowa Main", 25, 150.00));
        resources.add(new Room("Pokój rekrutacyjny", 4, 45.00));
        resources.add(new Room("Sala kreacyjna Brainstorm", 8, 70.00));
        resources.add(new Room("Aula prezentacyjna", 50, 250.00));
        resources.add(new Room("Kameralny pokój spotkań", 3, 40.00));
        resources.add(new Room("Sala zarządowa VIP", 12, 120.00));
        resources.add(new Room("Pokój warsztatowy", 15, 95.00));
        resources.add(new Room("Micro-meeting booth", 2, 30.00));


        resources.add(new Desk("Biurko przy oknie", "hot", 15.50));
        resources.add(new Desk("Biurko w strefie cichej", "fixed", 25.00));
        resources.add(new Desk("Stanowisko z dwoma monitorami", "hot", 18.00));
        resources.add(new Desk("Biurko narożne PREMIUM", "fixed", 30.00));
        resources.add(new Desk("Biurko z regulacją wysokości", "hot", 20.00));
        resources.add(new Desk("Dedykowana stacja robocza A1", "fixed", 28.50));
        resources.add(new Desk("Biurko w open space", "hot", 12.00));
        resources.add(new Desk("Stanowisko programistyczne", "fixed", 32.00));
        resources.add(new Desk("Biurko blisko kuchni", "hot", 14.50));
        resources.add(new Desk("Dedykowana stacja robocza B2", "fixed", 27.00));

        resources.add(new Device("Projektor multimedialny 4K", 2, 25.00));
        resources.add(new Device("Flipchart z zapasem papieru", 5, 8.50));
        resources.add(new Device("Zestaw do wideokonferencji (kamera + mikrofon)", 1, 50.00));
        resources.add(new Device("Dodatkowy monitor 27 cali", 10, 5.00));
        resources.add(new Device("Drukarka 3D Bambu Lab", 1, 40.00));
        resources.add(new Device("Zestaw VR (Meta Quest 3)", 2, 35.00));
        resources.add(new Device("Mikrofon pojemnościowy do podcastów", 4, 15.00));
        resources.add(new Device("Tablica suchościeralna mobilna", 3, 12.00));
        resources.add(new Device("Kamera cyfrowa ze statywem", 2, 30.00));
        resources.add(new Device("Przenośny ekran projekcyjny", 3, 10.00));

        System.out.println("resources.findByName(\"biurko\") = " + resources.findByName("biurko"));
        System.out.println("resources.findByName(\"Biurko w strefie cichej\") = " + resources.findByName("Biurko w strefie cichej"));
        System.out.println("resources.findAll() = " + resources.findAll());
        System.out.println("resources.findAll().size() = " + resources.findAll().size());

        //TODO Tests for bookings
    }
}
