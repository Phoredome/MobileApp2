package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.awt.font.TextAttribute;


public class MyDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "CarApp.db";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyDB","Constructor");
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + TableEntry.USER_TABLE_NAME + " (" +
                    TableEntry.USER_COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.USER_COLUMN_NAME_FN + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_LN + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_EMAIL + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_ADDRESS + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_CITY + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_COUNTRY + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_PHONE_NUMBER + " NUMERIC," +
                    TableEntry.USER_COLUMN_NAME_DATE_OF_BIRTH + " NUMERIC," +
                    TableEntry.USER_COLUMN_NAME_PAYMENT_OPTIONS + " TEXT," +
                    TableEntry.USER_COLUMN_NAME_USERNAME + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_PASSWORD + " TEXT NOT NULL," +
                    TableEntry.USER_COLUMN_NAME_STATUS + " TEXT NOT NULL)";

    private static final String SQL_CREATE_ENTRIES_CAR =
            "CREATE TABLE " + TableEntry.CAR_TABLE_NAME + " (" +
                    TableEntry.CAR_COLUMN_NAME_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.CAR_COLUMN_NAME_VEHICLE_TYPE + " TEXT NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_COST_OF_RUNNING + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_SEATS + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_DOORS + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_LICENSE_PLATE + " TEXT NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_SERVICE_TIME + " NUMERIC NOT NULL," +
                    TableEntry.CAR_COLUMN_NAME_KMS_RUN + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_KMS_SINCE_LAST_SERVICE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_IN_USE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_IN_SERVICE + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_COORDX + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_COORDY + " NUMERIC," +
                    TableEntry.CAR_COLUMN_NAME_IN_STATION + " NUMERIC)";

    private static final String SQL_CREATE_ENTRIES_TRIP =
            "CREATE TABLE " + TableEntry.TRIP_TABLE_NAME + " (" +
                    TableEntry.TRIP_COLUMN_NAME_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.TRIP_COLUMN_NAME_CAR_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_USER_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_AMOUNT + " NUMERIC," +
                    TableEntry.TRIP_COLUMN_NAME_KMS_RUN_FOR_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_TIME_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_DATE_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGY + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGY + " NUMERIC NOT NULL," +
                    "FOREIGN KEY(carId) REFERENCES Car(carId)," +
                    "FOREIGN KEY(userId) REFERENCES User(userId))";

    private static final String SQL_CREATE_ENTRIES_STATION =
            "CREATE TABLE " + TableEntry.STATION_TABLE_NAME + " (" +
                    TableEntry.STATION_COLUMN_NAME_STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.STATION_COLUMN_NAME_CARS_AT_STATION + " INTEGER," +
                    TableEntry.STATION_COLUMN_NAME_LOCATION_X + " NUMERIC," +
                    TableEntry.STATION_COLUMN_NAME_LOCATION_Y + " NUMERIC," +
                    TableEntry.STATION_COLUMN_NAME_STATION_ACTIVE + " NUMERIC)";

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + TableEntry.USER_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_CAR =
            "DROP TABLE IF EXISTS " + TableEntry.CAR_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_TRIP =
            "DROP TABLE IF EXISTS " + TableEntry.TRIP_TABLE_NAME;


    private static final String SQL_DELETE_ENTRIES_STATION =
            "DROP TABLE IF EXISTS " + TableEntry.STATION_TABLE_NAME;

    private static final String SQL_INSERT_USER =
                    "insert into User (userName, password, firstName, lastName, email, phoneNumber, address, city, country, dateOfBirth, paymentOptions, status) " +
                            "values ('admin', 'admin', 'Bosco', 'Li', 'LiB@Cars.com', '602-922-6011', '72 Shelley Trail', 'Merdeka', 'Indonesia', '16/04/1955', 'jcb', 'true')," +
                            "('user', 'user', 'Young', 'Wong', 'ZoomZoomMaster@Cars.com', '711-185-0632', '6542 Dexter Place', 'Tambopata', 'Peru', '09/10/1912', 'mastercard', 'false')," +
                            "('klboo', 'klboo', 'Kristy', 'Le', 'Pikachuuu@Cars.com', '879-131-2047', '977 Cottonwood Court', 'Kembangkerang Lauk Timur', 'Indonesia', '15/04/1913', 'maestro', 'true')," +
                            "('ztreagust3', 'Y3Z9XvXep', 'Zulema', 'Treagust', 'ztreagust3@sbwire.com', '319-661-3764', '9 Rusk Hill', 'Glagahdowo', 'Indonesia', '14/02/1976', 'visa', 'false')," +
                            "('cweben4', 'YOjr2I', 'Cassandre', 'Weben', 'cweben4@liveinternet.ru', '799-799-9198', '2957 Steensland Junction', 'Kajar', 'Indonesia', '21/07/1912', 'mastercard', 'true')," +
                            "('wpidgeley5', '8vEO1XJ5j', 'Winnah', 'Pidgeley', 'wpidgeley5@marketwatch.com', '217-691-2860', '66461 Vahlen Terrace', 'Hengjian', 'China', '24/03/2011', 'visa-electron', 'true')," +
                            "('apriditt6', 'bjHW5C1', 'Ari', 'Priditt', 'apriditt6@aol.com', '948-710-4698', '1 Dakota Place', 'Balbagay', 'China', '15/07/1921', 'mastercard', 'true')," +
                            "('kguly7', 'YrGJlgb', 'Katusha', 'Guly', 'kguly7@istockphoto.com', '481-546-8027', '46 Jackson Parkway', 'Pedro Sánchez', 'Dominican Republic', '27/08/1918', 'americanexpress', 'false')," +
                            "('sbignall8', 'tmo52mlYk', 'Saidee', 'Bignall', 'sbignall8@prweb.com', '698-429-0718', '09220 Meadow Valley Center', 'Itagüí', 'Colombia', '26/05/1922', 'jcb', 'true')," +
                            "('wfilchakov9', 'EH9PguvoOxX', 'Wynn', 'Filchakov', 'wfilchakov9@alibaba.com', '322-607-2936', '61224 Muir Road', 'Kamenjane', 'Macedonia', '21/05/1912', 'mastercard', 'false')," +
                            "('chensa', '71SsFJHkBq9', 'Charin', 'Hens', 'chensa@mlb.com', '118-482-8646', '923 Lakewood Gardens Pass', 'Chiryū', 'Japan', '27/01/1977', 'jcb', 'true')," +
                            "('agibkeb', 'Q40Q57HHT', 'Alva', 'Gibke', 'agibkeb@huffingtonpost.com', '514-369-0337', '72141 Birchwood Circle', 'Solikamsk', 'Russia', '27/10/2018', 'jcb', 'true')," +
                            "('nmathiasc', '69aZxu4B9', 'Nikolas', 'Mathias', 'nmathiasc@imageshack.us', '628-372-3287', '02340 Alpine Point', 'Gaojingzhuang', 'China', '05/06/2011', 'jcb', 'false')," +
                            "('abottelld', 'eezwswScIM', 'Abbe', 'Bottell', 'abottelld@e-recht24.de', '474-759-4599', '8 Mifflin Parkway', 'København', 'Denmark', '13/10/2017', 'mastercard', 'true')," +
                            "('apeirsone', 'MwuV0sOcE', 'Annora', 'Peirson', 'apeirsone@loc.gov', '632-945-7087', '845 Lyons Plaza', 'Popovo', 'Russia', '30/04/1958', 'jcb', 'true')," +
                            "('mdykaf', 'bIOJQdX', 'Marie', 'Dyka', 'mdykaf@live.com', '359-697-3329', '1 Mallory Way', 'São Lourenço de Mamporcão', 'Portugal', '14/01/1963', 'visa', 'true')," +
                            "('kfarncombeg', 'E6B0LX6aCVg', 'Kincaid', 'Farncombe', 'kfarncombeg@tripod.com', '145-993-4820', '132 Springs Park', 'Cileueur', 'Indonesia', '20/05/1937', 'maestro', 'true')," +
                            "('domoylanh', 'vl8Luu', 'Darren', 'O''Moylan', 'domoylanh@myspace.com', '260-719-3521', '165 Sherman Lane', 'Aminga', 'Argentina', '25/01/1956', 'jcb', 'true')," +
                            "('rlouchi', 'c1B3gy', 'Roana', 'Louch', 'rlouchi@yellowpages.com', '259-801-0655', '530 Oxford Point', 'Doruchów', 'Poland', '14/12/2009', 'jcb', 'true')," +
                            "('bedesj', 'DpHyooBi', 'Betsey', 'Edes', 'bedesj@w3.org', '237-383-8645', '45 Sommers Avenue', 'Huilong', 'China', '22/03/1973', 'americanexpress', 'false')," +
                            "('ksauntonk', '0rZgiNY', 'Keelia', 'Saunton', 'ksauntonk@comcast.net', '210-104-1844', '91 Daystar Hill', 'Studená', 'Czech Republic', '01/04/1927', 'diners-club-enroute', 'true')," +
                            "('zgiacobol', 'd1xNpO', 'Zuzana', 'Giacobo', 'zgiacobol@mediafire.com', '696-359-1237', '0606 Autumn Leaf Drive', 'Tipitapa', 'Nicaragua', '29/09/1985', 'jcb', 'true')," +
                            "('vbroaderm', 'fJkDIas0pLMJ', 'Vera', 'Broader', 'vbroaderm@japanpost.jp', '398-657-1696', '19 Shasta Park', 'Mūsá Qal‘ah', 'Afghanistan', '31/07/1990', 'bankcard', 'true')," +
                            "('rgotliffen', '8NuuYptfr1z0', 'Raynard', 'Gotliffe', 'rgotliffen@imageshack.us', '544-926-5492', '79 Doe Crossing Crossing', 'Guruyan', 'Philippines', '10/05/1988', 'visa-electron', 'false')," +
                            "('mformiglio', 'OObwkNzfepUj', 'Mirilla', 'Formigli', 'mformiglio@cbsnews.com', '979-947-5309', '63 Valley Edge Junction', 'Cilentung', 'Indonesia', '14/06/2003', 'visa-electron', 'true')," +
                            "('blupartop', '6IwrnoZe', 'Byran', 'Luparto', 'blupartop@mysql.com', '683-257-7469', '28235 Cardinal Place', 'Si Bun Rueang', 'Thailand', '06/12/1954', 'visa-electron', 'true')," +
                            "('tborzoneq', '1nXswYJLEXY', 'Tasia', 'Borzone', 'tborzoneq@liveinternet.ru', '443-615-8642', '9677 Pennsylvania Point', 'Cullinan', 'South Africa', '31/08/1981', 'jcb', 'true')," +
                            "('rmcgrieler', 'ZS3KRtX', 'Reid', 'McGriele', 'rmcgrieler@cisco.com', '463-535-8441', '7103 Dunning Drive', 'Slobozia', 'Moldova', '12/03/1942', 'americanexpress', 'false')," +
                            "('gdeshons', '0Ij3YN', 'Gussie', 'Deshon', 'gdeshons@wikipedia.org', '674-932-2046', '5 Sachtjen Plaza', 'Drybin', 'Belarus', '20/03/1968', 'visa', 'true')," +
                            "('astubst', 'lMgDMoZOG', 'Allie', 'Stubs', 'astubst@icio.us', '566-384-1312', '84667 Raven Junction', 'Rešetari', 'Croatia', '27/04/1965', 'jcb', 'true')," +
                            "('rstammersu', 'gyBr19hg', 'Rafe', 'Stammers', 'rstammersu@usnews.com', '328-541-6894', '0 Summerview Terrace', 'San Antonio', 'Mexico', '03/12/1928', 'instapayment', 'false')," +
                            "('vgrovierv', 'SeHad6FY', 'Viviyan', 'Grovier', 'vgrovierv@wikipedia.org', '708-152-2743', '76 Sutteridge Trail', 'Machetá', 'Colombia', '12/04/1994', 'switch', 'true')," +
                            "('dlarmettw', 'T8yiDx', 'Dorie', 'Larmett', 'dlarmettw@shinystat.com', '642-634-5671', '0420 Nancy Place', 'Prachuap Khiri Khan', 'Thailand', '14/12/1926', 'china-unionpay', 'false')," +
                            "('osuttlex', '9qR0BMKLx', 'Osborne', 'Suttle', 'osuttlex@bandcamp.com', '322-494-7284', '63 Meadow Vale Lane', 'Al Qaţn', 'Yemen', '20/06/1919', 'diners-club-carte-blanche', 'true')," +
                            "('scottisy', '3bQM2j', 'Sigfried', 'Cottis', 'scottisy@bloglovin.com', '752-621-4048', '328 1st Parkway', 'Tulsīpur', 'Nepal', '16/08/1994', 'jcb', 'true')," +
                            "('dhatherillz', '0Iff8BxTqFqQ', 'Dido', 'Hatherill', 'dhatherillz@salon.com', '483-565-4448', '405 Briar Crest Center', 'Skoura', 'Morocco', '30/10/1977', 'maestro', 'false')," +
                            "('cgambrell10', 'at7tVm', 'Correy', 'Gambrell', 'cgambrell10@delicious.com', '298-786-2177', '9 Sutteridge Crossing', 'Krasnoshchekovo', 'Russia', '02/04/1996', 'jcb', 'false')," +
                            "('rlackemann11', 'BYBs85', 'Ronni', 'Lackemann', 'rlackemann11@github.com', '741-745-0615', '3925 Merry Way', 'Zhoukou', 'China', '29/04/2005', 'diners-club-enroute', 'true')," +
                            "('arawes12', 'LGiUfS0ZGvj', 'Alix', 'Rawes', 'arawes12@forbes.com', '508-612-9577', '30792 Ohio Park', 'Shimen', 'China', '26/11/1948', 'visa-electron', 'false')," +
                            "('tkesper13', 'zr4SXUv6', 'Tawnya', 'Kesper', 'tkesper13@fc2.com', '563-202-7702', '77161 Veith Center', 'Colonia Wanda', 'Argentina', '13/03/1951', 'diners-club-carte-blanche', 'true')," +
                            "('tyukhov14', 'GkPDmM2VO5c5', 'Tamiko', 'Yukhov', 'tyukhov14@reddit.com', '160-407-8566', '0434 Delladonna Way', 'Dachuan', 'China', '19/05/1944', 'jcb', 'false')," +
                            "('rclouston15', 'vgKFBBXHk', 'Reyna', 'Clouston', 'rclouston15@csmonitor.com', '643-964-7483', '72449 Raven Park', 'Jami', 'Indonesia', '26/04/1935', 'bankcard', 'false')," +
                            "('ptimperley16', 'DkGjVyLYSv', 'Perice', 'Timperley', 'ptimperley16@xing.com', '724-156-3090', '4 Florence Center', 'Jinanten', 'Indonesia', '29/01/1955', 'americanexpress', 'false')," +
                            "('mbrockie17', 'BYnWUH', 'Maiga', 'Brockie', 'mbrockie17@cargocollective.com', '760-158-8831', '722 Lake View Street', 'Huaraz', 'Peru', '07/12/1913', 'china-unionpay', 'false')," +
                            "('hcoping18', 't9UbMIKf5T', 'Humphrey', 'Coping', 'hcoping18@mediafire.com', '533-254-6102', '552 Lukken Court', 'Gambēla', 'Ethiopia', '04/05/1985', 'china-unionpay', 'false')," +
                            "('varends19', '4aejT3', 'Vivianne', 'Arends', 'varends19@eventbrite.com', '526-224-6104', '1 Mockingbird Alley', 'Longonjo', 'Angola', '06/03/1975', 'visa-electron', 'false')," +
                            "('mhadleigh1a', 'k3nxxW1h', 'Myrilla', 'Hadleigh', 'mhadleigh1a@scientificamerican.com', '130-703-0739', '3 Sunbrook Way', 'Stratónion', 'Greece', '18/10/1978', 'maestro', 'true')," +
                            "('msterland1b', '8XNGRVBAKxn6', 'Melissa', 'Sterland', 'msterland1b@1688.com', '927-462-7285', '3781 Amoth Road', 'Ganghwa-gun', 'South Korea', '18/10/1986', 'jcb', 'true')," +
                            "('sburstow1c', 'B3AD5FzL4Zk0', 'Seka', 'Burstow', 'sburstow1c@wunderground.com', '287-578-9585', '76982 Eggendart Plaza', 'Stockholm', 'Sweden', '05/08/1984', 'jcb', 'true')," +
                            "('nburston1d', 'hgVBkuMZ7oKJ', 'Nelle', 'Burston', 'nburston1d@google.com', '762-633-3773', '3596 Towne Way', 'Páno Polemídia', 'Cyprus', '01/01/1968', 'switch', 'true')," +
                            "('clum1e', 'XTDEuJE', 'Cecil', 'Lum', 'clum1e@weather.com', '991-429-2129', '0 Drewry Plaza', 'Yinzhu', 'China', '04/07/1928', 'jcb', 'true')," +
                            "('gdunridge1f', 'Lj6siMMVuA', 'Galina', 'Dunridge', 'gdunridge1f@clickbank.net', '586-532-3721', '9961 Truax Center', 'São Paulo', 'Brazil', '18/01/2002', 'switch', 'false')," +
                            "('jgummory1g', 'YqKwT0e', 'Jojo', 'Gummory', 'jgummory1g@microsoft.com', '107-541-0583', '7 Schmedeman Street', 'Luchegorsk', 'Russia', '07/04/1976', 'china-unionpay', 'false')," +
                            "('sjakaway1h', 'Yc9WYE', 'Stewart', 'Jakaway', 'sjakaway1h@e-recht24.de', '674-801-3341', '585 Killdeer Court', 'Željezno Polje', 'Bosnia and Herzegovina', '10/06/1967', 'bankcard', 'true')," +
                            "('lkurth1i', 'GhbcdwqM2', 'Loren', 'Kurth', 'lkurth1i@jalbum.net', '347-815-3597', '4 Westend Trail', 'Pangkalan', 'Indonesia', '23/02/1936', 'americanexpress', 'true')," +
                            "('mperrinchief1j', '7wsUcV', 'Merna', 'Perrinchief', 'mperrinchief1j@sphinn.com', '797-463-9090', '88923 Northview Pass', 'Edissiya', 'Russia', '11/01/1944', 'jcb', 'true')," +
                            "('lheinish1k', 'gC9S6Y4Lxom', 'Leeanne', 'Heinish', 'lheinish1k@xing.com', '143-375-0118', '5 Talisman Road', 'Vologda', 'Russia', '27/09/1973', 'maestro', 'false')," +
                            "('kmcenhill1l', 'x9jOSC', 'Kara-lynn', 'McEnhill', 'kmcenhill1l@google.fr', '136-212-7074', '379 Holy Cross Road', 'Ciangir', 'Indonesia', '30/06/2011', 'mastercard', 'false')," +
                            "('rfoggarty1m', 'DAASuzY', 'Red', 'Foggarty', 'rfoggarty1m@moonfruit.com', '817-522-9232', '29107 La Follette Lane', 'Agusan', 'Philippines', '26/07/1982', 'jcb', 'false')," +
                            "('bmedler1n', 'ZTZ27xW0f', 'Brenna', 'Medler', 'bmedler1n@wikipedia.org', '110-557-4100', '0278 Autumn Leaf Plaza', 'Mayong', 'China', '09/07/1976', 'instapayment', 'false')," +
                            "('spelerin1o', 'gWjNXK', 'Susanne', 'Pelerin', 'spelerin1o@mapy.cz', '731-343-1937', '363 2nd Plaza', 'Tygda', 'Russia', '07/03/2001', 'jcb', 'false')," +
                            "('rpyner1p', '7SM9GKkoO', 'Rivy', 'Pyner', 'rpyner1p@ebay.com', '317-557-6961', '116 Morrow Park', 'Anping', 'China', '22/08/1955', 'diners-club-enroute', 'false')," +
                            "('gzamora1q', 'xnz0kOH6n7', 'Georgina', 'Zamora', 'gzamora1q@nps.gov', '530-383-1512', '12 East Way', 'Żórawina', 'Poland', '08/04/1920', 'visa-electron', 'true')," +
                            "('dswainger1r', 'zIW3vtVeM9g', 'Dania', 'Swainger', 'dswainger1r@aboutads.info', '769-118-9124', '6201 Becker Terrace', 'Yanshi', 'China', '06/09/1966', 'mastercard', 'false')," +
                            "('vtidgewell1s', 'lwOuCfAjIz22', 'Viviana', 'Tidgewell', 'vtidgewell1s@xing.com', '572-351-5547', '152 Superior Road', 'Krzanowice', 'Poland', '20/06/2012', 'diners-club-us-ca', 'true')," +
                            "('swiggall1t', 'RSBg4hIQ', 'Sherrie', 'Wiggall', 'swiggall1t@un.org', '442-797-5111', '09 Arrowood Parkway', 'Bouillon', 'Belgium', '17/10/1974', 'mastercard', 'false')," +
                            "('draden1u', 'LBpmKX8mID', 'Drew', 'Raden', 'draden1u@icq.com', '372-191-2555', '6 Meadow Vale Court', 'Tegalgunung', 'Indonesia', '25/11/1951', 'jcb', 'false')," +
                            "('mstennine1v', 'yclgAM', 'Malina', 'Stennine', 'mstennine1v@nasa.gov', '748-269-5520', '382 Jenna Avenue', 'Gamay', 'Philippines', '15/10/1995', 'mastercard', 'true')," +
                            "('rvannucci1w', 'CxYc3hQyn', 'Robbyn', 'Vannucci', 'rvannucci1w@hhs.gov', '428-505-1054', '7974 Miller Hill', 'Zaporizhzhya', 'Ukraine', '07/05/2007', 'mastercard', 'false')," +
                            "('sleser1x', '1nht2J', 'Saleem', 'Leser', 'sleser1x@live.com', '677-714-8311', '69088 Sutteridge Parkway', 'Cojutepeque', 'El Salvador', '11/08/1941', 'switch', 'true')," +
                            "('bbresson1y', '9ch5aR', 'Becka', 'Bresson', 'bbresson1y@dell.com', '945-200-6564', '681 Oneill Park', 'Gaoqiao', 'China', '02/12/1937', 'maestro', 'true')," +
                            "('dgobert1z', '3QI3kg', 'Dar', 'Gobert', 'dgobert1z@youku.com', '784-742-5020', '43445 School Trail', 'Cilaja', 'Indonesia', '21/08/1949', 'jcb', 'true')," +
                            "('sbodman20', 'VCqTVRW7', 'Sheryl', 'Bodman', 'sbodman20@desdev.cn', '337-590-3943', '316 8th Park', 'Beigong', 'China', '08/06/2013', 'instapayment', 'true')," +
                            "('dsampey21', 'tHBYuh6V9R', 'Darryl', 'Sampey', 'dsampey21@infoseek.co.jp', '415-162-2878', '534 Evergreen Court', 'San Rafael', 'United States', '03/02/1930', 'bankcard', 'true')," +
                            "('nscourfield22', 'gz92QWCVCsM', 'Norri', 'Scourfield', 'nscourfield22@trellian.com', '990-682-7223', '5 Sachtjen Street', 'Grahamstown', 'South Africa', '30/04/1976', 'diners-club-enroute', 'true')," +
                            "('bplacidi23', '0QXkOE8eQC', 'Barbaraanne', 'Placidi', 'bplacidi23@buzzfeed.com', '650-671-9802', '90 Algoma Way', 'Regulice', 'Poland', '05/11/2015', 'instapayment', 'true')," +
                            "('obyram24', 'A96T6SjMm', 'Obediah', 'Byram', 'obyram24@answers.com', '487-698-3324', '623 Springs Crossing', 'Yonglong', 'China', '29/01/1978', 'switch', 'true')," +
                            "('rsennett25', 'KsKmwAwK', 'Roarke', 'Sennett', 'rsennett25@flickr.com', '332-107-0532', '03 Bluejay Avenue', 'Belyye Stolby', 'Russia', '13/04/1986', 'maestro', 'false')," +
                            "('awand26', 'YJMAIsp8MsET', 'Alan', 'Wand', 'awand26@slashdot.org', '376-253-1068', '4861 4th Hill', 'Chhātak', 'Bangladesh', '15/12/1992', 'jcb', 'true')," +
                            "('stofts27', 'c82vKl', 'Sibbie', 'Tofts', 'stofts27@marketwatch.com', '843-209-2431', '70 Columbus Lane', 'Beaufort', 'United States', '10/06/1984', 'jcb', 'true')," +
                            "('mfilby28', 'ZpF3jq9', 'Marika', 'Filby', 'mfilby28@hugedomains.com', '380-991-2611', '8047 Mockingbird Trail', 'Dordrecht', 'Netherlands', '28/10/2002', 'jcb', 'true')," +
                            "('eronchi29', 'XSV4IZ', 'Emiline', 'Ronchi', 'eronchi29@blinklist.com', '153-718-6900', '5 Jana Point', 'Cergy-Pontoise', 'France', '10/03/1948', 'jcb', 'true')," +
                            "('mqualtrough2a', 'FKNVpu', 'Melosa', 'Qualtrough', 'mqualtrough2a@behance.net', '560-831-6681', '8035 Haas Terrace', 'Vrsi', 'Croatia', '04/10/1999', 'visa-electron', 'true')," +
                            "('doris2b', 'Xa8tvufVHz', 'Dennie', 'Oris', 'doris2b@home.pl', '725-464-9706', '64 Meadow Valley Circle', 'Pimentel', 'Dominican Republic', '11/07/1941', 'maestro', 'true')," +
                            "('pdoerren2c', 'nKS46Qqve5t', 'Pat', 'Doerren', 'pdoerren2c@hibu.com', '984-776-0041', '5 Dunning Terrace', 'Lidzbark', 'Poland', '13/11/1972', 'diners-club-us-ca', 'true')," +
                            "('sdenne2d', 'kNrQkr', 'Staci', 'Denne', 'sdenne2d@prnewswire.com', '283-510-9182', '48424 Luster Circle', 'Made', 'Indonesia', '09/08/1968', 'switch', 'false')," +
                            "('cbrockway2e', 'POSqmDF', 'Cherilyn', 'Brockway', 'cbrockway2e@addtoany.com', '967-304-5879', '8 Rockefeller Terrace', 'Krajan Dua Padomasan', 'Indonesia', '17/05/1919', 'jcb', 'true')," +
                            "('ccaddell2f', 'eVpCfONG', 'Cyndi', 'Caddell', 'ccaddell2f@csmonitor.com', '486-940-0080', '34 Esch Parkway', 'Huangtan', 'China', '17/03/1968', 'instapayment', 'true')," +
                            "('echason2g', 'FbHsn4kEEd', 'Ethel', 'Chason', 'echason2g@arstechnica.com', '696-173-6095', '6 Montana Avenue', 'Shuitou', 'China', '20/11/1914', 'americanexpress', 'true')," +
                            "('alomasney2h', 'UvSsLD0FfT5', 'Araldo', 'Lomasney', 'alomasney2h@boston.com', '119-610-0748', '1 Declaration Street', 'Telgawah', 'Indonesia', '10/05/1932', 'jcb', 'true')," +
                            "('rmacshane2i', 'Kv9cMCfPtP', 'Ruperta', 'MacShane', 'rmacshane2i@thetimes.co.uk', '830-194-1792', '36 Ridge Oak Place', 'Vila Franca', 'Portugal', '10/06/1929', 'diners-club-carte-blanche', 'false')," +
                            "('wkun2j', 'JhAvZLqKiJz', 'Willyt', 'Kun', 'wkun2j@ucla.edu', '690-466-9913', '9 Little Fleur Drive', 'Dahe', 'China', '25/03/1960', 'jcb', 'true')," +
                            "('adriutti2k', 'Rz6idoHhLy', 'Arte', 'Driutti', 'adriutti2k@google.com', '712-410-3701', '73945 Luster Court', 'Al Qanāţir al Khayrīyah', 'Egypt', '25/07/2007', 'laser', 'true')," +
                            "('kembury2l', 'EncDRopyrM', 'Kimball', 'Embury', 'kembury2l@plala.or.jp', '502-366-7955', '11 Alpine Court', 'Utrecht (stad)', 'Netherlands', '30/10/1945', 'switch', 'true')," +
                            "('dbatchelder2m', 't181pBzNZB', 'Dorey', 'Batchelder', 'dbatchelder2m@sphinn.com', '577-837-1334', '0483 Manufacturers Point', 'Kompaniyivka', 'Ukraine', '10/07/1977', 'jcb', 'false')," +
                            "('mvanderkruijs2n', 'f594aAU', 'Marillin', 'Van der Kruijs', 'mvanderkruijs2n@istockphoto.com', '986-150-3780', '25 Pennsylvania Street', 'Sattahip', 'Thailand', '22/11/1916', 'mastercard', 'true')," +
                            "('abraunes2o', '1dR9d3suSU8', 'Adriaens', 'Braunes', 'abraunes2o@wsj.com', '526-230-1337', '9 Arapahoe Lane', 'Liutang', 'China', '12/12/1951', 'jcb', 'true')," +
                            "('npeckitt2p', 'z1hdUS8uEds', 'Nonnah', 'Peckitt', 'npeckitt2p@ibm.com', '203-125-3815', '13095 Golf Course Trail', 'Raksajaya', 'Indonesia', '16/06/1927', 'jcb', 'false')," +
                            "('mwindress2q', 'o5t27TI5U1P', 'Michel', 'Windress', 'mwindress2q@mozilla.org', '343-720-7344', '914 Golf View Terrace', 'Sóc Sơn', 'Vietnam', '25/10/1950', 'jcb', 'true')," +
                            "('lmucci2r', 'ybtDVjKmX', 'Luci', 'Mucci', 'lmucci2r@wikimedia.org', '743-168-9557', '88340 Hauk Place', 'Jawornik', 'Poland', '11/11/1967', 'jcb', 'true');";


    private static final String SQL_INSERT_CAR =
            "insert into Car (costOfRunning, seats, doors, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inActiveService, coordX, coordY) " +
                    "values ('2.51', 2, 2, 20, 199291, 8641, 'Escape', '6d958d', 'false', 'true', 40.7143528, -74.0059731)," +
                    "('1.65', 8, 2, 11, 142402, 5387, 'Ram Van 1500', '33908d', 'true', 'true', 34.2506515, 36.0117322)," +
                    "('2.21', 8, 2, 9, 185193, 7957, '5 Series', 'ddac5d', 'true', 'false', 32.417346, 105.238901)," +
                    "('2.20', 8, 2, 5, 129059, 6004, 'Town Car', 'b52988', 'true', 'true', 13.5570718, -88.3494328)," +
                    "('3.33', 8, 2, 16, 149470, 15982, 'Bravada', '8ca592', 'false', 'true', 14.6592829, 121.0483527)," +
                    "('2.26', 2, 4, 20, 150495, 7915, 'Phantom', '1edce6', 'true', 'true', 27.141681, 105.613174)," +
                    "('2.50', 2, 4, 7, 48660, 6811, 'LeMans', '1627d2', 'true', 'true', 30.1172159, 55.1210303)," +
                    "('1.41', 8, 2, 17, 191526, 9033, '240SX', '5f1d1d', 'false', 'false', 40.6816244, -8.6291241)," +
                    "('1.53', 5, 2, 3, 126857, 9474, 'Stratus', 'e08846', 'true', 'false', 28.168408, 120.482198)," +
                    "('3.80', 2, 2, 10, 76800, 18438, 'H1', 'a5d58c', 'false', 'true', 41.276688, 80.238959)," +
                    "('2.02', 8, 4, 9, 183816, 15359, 'Cougar', 'e2dbaf', 'false', 'false', 13.312811, 121.8790306)," +
                    "('3.77', 2, 4, 2, 63167, 9359, 'Monte Carlo', '3e974d', 'false', 'false', 50.5037851, 14.7986888)," +
                    "('2.99', 2, 2, 17, 174630, 19252, '370Z', 'ec1d33', 'true', 'false', -17.5872806, 48.2219395)," +
                    "('3.14', 5, 2, 3, 194415, 10905, 'Range Rover Sport', '79be95', 'true', 'false', 40.420237, 114.090867)," +
                    "('4.13', 8, 2, 2, 79087, 15769, 'Miata MX-5', '4ed16e', 'true', 'true', 53.7532734, 28.0135008)," +
                    "('3.94', 5, 4, 18, 79558, 13568, 'SJ 410', '65f2f3', 'false', 'true', 51.8063451, 26.0265105)," +
                    "('0.60', 8, 2, 9, 25541, 1153, 'Camry', 'dc5924', 'true', 'true', 21.493713, 110.404001)," +
                    "('4.94', 5, 2, 12, 159411, 10434, 'Express 2500', '591d06', 'true', 'false', 27.974248, 120.632896)," +
                    "('4.53', 8, 4, 12, 189050, 15163, 'Yukon XL 2500', 'd5e392', 'false', 'true', -43.1833, -71.8667)," +
                    "('1.91', 5, 4, 14, 87627, 4935, 'Continental Flying Spur', '28b1ff', 'false', 'true', '5.91677', '7.67615')," +
                    "('4.32', 5, 2, 5, 112206, 10, '900', '1708cc', 'false', 'false', 14.6171091, 120.986275)," +
                    "('2.02', 5, 4, 4, 114882, 3745, 'EXP', '5d7081', 'false', 'true', 53.8035238, 86.8634095)," +
                    "('4.55', 8, 4, 20, 144349, 4752, 'D250', '1c8110', 'false', 'false', 23.259479, 113.824887)," +
                    "('2.68', 5, 4, 11, 94379, 11882, 'Corvette', '403c9e', 'true', 'false', -6.9841118, 111.7048262)," +
                    "('0.16', 8, 2, 15, 8647, 8674, 'E350', 'ccb077', 'false', 'true', 10.1206819, 122.9809409)," +
                    "('4.01', 8, 2, 3, 71094, 10533, 'Regal', 'bdad57', 'true', 'false', -7.9110809, 111.4253892)," +
                    "('1.04', 8, 2, 20, 19620, 12229, 'Navigator L', '02b6a3', 'true', 'true', -27.3900758, -55.9207015)," +
                    "('0.72', 8, 4, 13, 106588, 5170, 'Ram 3500', '144bc3', 'true', 'true', -26.5380045, -57.0421483)," +
                    "('4.39', 8, 2, 18, 37236, 5544, 'Amigo', '220279', 'false', 'true', 9.9368373, 123.9469641)," +
                    "('1.84', 8, 2, 9, 156960, 2358, 'CTS', '6fe9a1', 'false', 'true', 8.7550575, -11.044307)," +
                    "('2.21', 2, 2, 4, 199090, 10583, 'Focus', '961b83', 'false', 'false', 31.678719, 35.241822)," +
                    "('1.99', 2, 2, 14, 61173, 3429, 'Cougar', '7a7ed9', 'false', 'false', 54.5107844, 53.4440122)," +
                    "('0.12', 5, 2, 9, 32979, 11708, 'Sportage', '46ad30', 'true', 'false', -23.9793102, -48.8768997)," +
                    "('2.54', 5, 2, 11, 136777, 5940, 'Swift', 'e3e6b0', 'true', 'true', 10.333974, -73.180718)," +
                    "('2.55', 8, 2, 6, 61915, 3979, 'Sixty Special', '179360', 'false', 'true', 32.964648, 35.495997)," +
                    "('0.52', 2, 4, 12, 129215, 11010, 'S60', '7e6839', 'false', 'true', -8.7334983, 121.7508912)," +
                    "('2.04', 5, 4, 15, 10696, 675, 'TT', 'cc6e48', 'false', 'false', 26.7044143, -80.1986649)," +
                    "('2.64', 5, 4, 12, 4842, 13290, 'Tucson', '302d2d', 'true', 'true', 39.9061718, 20.0613438)," +
                    "('3.06', 2, 2, 10, 62418, 16234, 'Sienna', '5c5f19', 'false', 'true', 45.8442734, 20.2867074)," +
                    "('1.19', 2, 4, 20, 52399, 288, 'Villager', '1c84e2', 'true', 'true', 40.9937134, -8.2760021)," +
                    "('0.85', 2, 4, 14, 78989, 19992, 'Matrix', 'f8945c', 'true', 'false', 35.8521978, 114.3530642)," +
                    "('0.81', 8, 4, 9, 45720, 11992, 'Solara', 'a6127e', 'false', 'true', '12.41086', '108.17682')," +
                    "('4.01', 2, 4, 7, 67448, 12152, 'R8', 'bdfabd', 'true', 'true', 43.9574882, 12.4552546)," +
                    "('2.16', 2, 4, 12, 134791, 4045, 'Explorer', '380f37', 'true', 'false', '41.49511', '22.38359')," +
                    "('4.85', 5, 4, 11, 11318, 413, 'riolet', '1fe8a7', 'true', 'false', 56.1688881, 15.6354869)," +
                    "('0.49', 2, 2, 5, 38324, 1099, 'Ranger', '1a6715', 'true', 'false', 40.5484721, 44.9618132)," +
                    "('0.24', 8, 2, 6, 82119, 13412, 'Fusion', '282387', 'false', 'true', 34.646636, 103.48159)," +
                    "('1.47', 2, 4, 14, 14783, 9872, 'Montero', '286ecf', 'true', 'true', 46.7391101, 30.7866668)," +
                    "('4.23', 2, 4, 2, 139645, 6474, 'Malibu', 'b4dd45', 'true', 'false', 59.375207, 17.0534187)," +
                    "('4.84', 8, 4, 4, 165340, 12623, 'Celica', '912b16', 'true', 'false', 34.329605, 108.708991)," +
                    "('1.21', 2, 4, 4, 101927, 16554, 'Beretta', '252afb', 'false', 'false', 13.2375414, -14.1959978)," +
                    "('3.91', 8, 2, 19, 38732, 9240, 'Safari', '0ee2f6', 'true', 'true', 55.499775, 25.635173)," +
                    "('0.41', 2, 2, 14, 36142, 19697, 'CR-V', 'd4301c', 'true', 'false', 27.859517, 114.758301)," +
                    "('1.25', 8, 4, 16, 133874, 6675, 'Sequoia', '2dd594', 'true', 'false', 49.4289115, 23.7472952)," +
                    "('4.37', 8, 4, 19, 115365, 19224, 'Yaris', '6f89c7', 'false', 'true', 14.1234415, -86.8697068)," +
                    "('1.85', 5, 4, 13, 167001, 12880, 'CTS', 'b0b147', 'false', 'false', 55.496478, 12.9658307)," +
                    "('0.79', 2, 2, 8, 45701, 10879, 'Bravada', '5420fe', 'true', 'true', -7.3175297, -63.9586111)," +
                    "('4.96', 8, 4, 12, 1490, 2094, 'GTO', '8a3ad4', 'true', 'false', 36.1763474, 139.5303855)," +
                    "('1.61', 8, 4, 4, 95556, 15850, 'Mazda3', '133886', 'true', 'false', -26.8193949, -65.1435685)," +
                    "('2.99', 5, 2, 6, 134137, 11786, 'M3', '89c554', 'true', 'false', -18.8549317, -41.9559233);";

    private static final String SQL_INSERT_TRIP =
            "insert into Trip (carId, userId, kmsRunForTrip, timeOfTrip, dateOfTrip, amount, startingY, startingX, endingX, endingY) values (41, 48, 92.94, '7:52 AM', '10/05/2019', null, 98.8087151, 2.6715181, 27.3115158, 30.9713333)," +
                    "(40, 11, 97.54, '11:31 PM', '31/07/2018', null, 127.4626534, 35.2024947, -14.4681236, -48.4560109)," +
                    "(41, 49, 0.6, '5:02 PM', '30/12/2018', null, 69.1447081, 35.4018071, -40.1382194, -71.2883704)," +
                    "(12, 47, 87.76, '3:00 AM', '28/09/2018', null, '-14.93333', '13.78333', 20.585863, -100.384181)," +
                    "(34, 48, 23.18, '6:32 AM', '10/07/2018', null, 2.1597595, 6.6644629, -22.5112638, -43.1779137)," +
                    "(18, 26, 88.69, '3:30 AM', '18/07/2018', null, -59.0746886, -37.3130297, -5.2171961, 120.112735)," +
                    "(10, 42, 22.37, '3:22 PM', '17/10/2018', null, 114.1264237, 30.7242628, 39.170826, 100.836184)," +
                    "(5, 23, 4.57, '6:50 AM', '18/05/2019', null, -71.4603621, -14.1462865, -10.1709258, 123.6059899)," +
                    "(5, 42, 31.77, '7:40 PM', '24/05/2019', null, 49.127197, 14.5404328, 44.019172, 18.1535702)," +
                    "(48, 13, 20.17, '9:07 PM', '08/11/2018', null, 108.736289, 18.578496, 56.7558466, 60.1103318)," +
                    "(41, 20, 3.8, '8:27 AM', '21/07/2018', null, 39.4489055, 55.9251242, 6.9130451, -73.9712488)," +
                    "(37, 79, 5.82, '9:10 AM', '06/06/2019', null, 115.429626, 30.203152, 13.8811133, 123.6886104)," +
                    "(32, 25, 59.4, '11:56 PM', '03/11/2018', null, 15.0266516, 50.7911439, -13.1435017, 28.4163103)," +
                    "(10, 20, 31.36, '7:27 PM', '30/07/2018', null, 97.3688438, 37.3745927, 30.2733296, 120.1672735)," +
                    "(44, 78, 51.96, '7:40 PM', '24/04/2019', null, 106.8396166, -6.2318126, 4.9224556, 13.2923232)," +
                    "(41, 77, 4.76, '5:12 PM', '11/11/2018', null, -89.713497, 14.335889, -27.4955748, -48.6553685)," +
                    "(26, 5, 56.21, '4:33 AM', '28/09/2018', null, 19.0070982, 45.7376939, 9.1968448, -75.8766333)," +
                    "(15, 83, 87.47, '4:04 AM', '19/01/2019', null, 5.9279895, 49.6597583, 41.3836182, -8.4170742)," +
                    "(12, 100, 41.61, '9:31 PM', '22/03/2019', null, 39.2, -7.9, 43.1557012, 22.5856811)," +
                    "(41, 97, 46.96, '3:59 PM', '19/08/2018', null, 106.8340622, -6.1671806, 23.082498, 113.518911)," +
                    "(21, 19, 84.12, '1:25 AM', '12/04/2019', null, 139.3741999, 36.1364291, 52.423321, 47.202499)," +
                    "(10, 74, 85.78, '11:55 PM', '08/02/2019', null, 153.5799639, 5.5775857, 38.838392, 99.617119)," +
                    "(4, 65, 84.25, '5:14 PM', '02/09/2018', null, 116.3169416, 39.7601611, -6.0912649, -35.4124355)," +
                    "(14, 8, 87.6, '3:43 AM', '01/11/2018', null, 113.717886, 26.930956, -2.321899, 110.5930548)," +
                    "(18, 40, 23.42, '4:26 PM', '27/03/2019', null, -47.37045, -21.4909206, 5.225936, -3.7536663)," +
                    "(20, 38, 71.62, '11:20 AM', '24/07/2018', null, -80.0367259, 40.4222843, 28.926001, 112.320499)," +
                    "(10, 19, 91.6, '8:49 PM', '23/10/2018', null, 21.4744125, 62.4232512, '-14.33056', '-170.75278')," +
                    "(30, 96, 64.77, '12:06 PM', '12/08/2018', null, -99.1082647, 19.4449916, 27.330904, 117.044081)," +
                    "(19, 31, 60.43, '10:21 PM', '22/01/2019', null, 28.8660119, 51.3870782, 10.142762, -85.454983)," +
                    "(58, 60, 92.44, '9:32 AM', '22/11/2018', null, 58.4814355, 51.2343307, 15.30487, 47.83894)," +
                    "(17, 39, 16.45, '3:07 AM', '12/01/2019', null, 14.6966832, 6.8344177, -7.1056569, 107.9005972)," +
                    "(27, 77, 72.63, '9:31 AM', '15/04/2019', null, 32.262317, 48.507933, -2.673867, 139.7728757)," +
                    "(20, 91, 14.06, '11:37 AM', '30/10/2018', null, -8.635246, 38.4305306, 51.3531567, 39.3015305)," +
                    "(53, 42, 36.77, '4:02 PM', '29/12/2018', null, 115.2580854, -8.6283422, -7.1927733, -48.204827)," +
                    "(9, 93, 6.87, '8:16 AM', '21/09/2018', null, 106.8974964, -6.3503773, 37.9440927, 139.3739616)," +
                    "(1, 3, 61.95, '5:07 AM', '19/05/2019', null, -9.0018424, 39.6832131, 45.521519, 3.5276642)," +
                    "(37, 68, 15.6, '12:15 PM', '13/11/2018', null, 37.5051617, 55.7422912, -34.5860444, -58.5529923)," +
                    "(9, 83, 78.5, '7:35 AM', '05/07/2018', null, 37.2229806, 56.0127743, 51.4492114, 7.1597986)," +
                    "(53, 55, 55.27, '12:11 PM', '28/01/2019', null, 16.1481061, 58.5812006, -5.98978, -37.9466737)," +
                    "(32, 72, 2.9, '6:27 PM', '21/02/2019', null, 98.633013, 28.541342, 7.6738728, 124.9935888)," +
                    "(30, 13, 95.16, '4:49 PM', '13/02/2019', null, 19.3629829, 44.3623348, 6.8344177, 14.6966832)," +
                    "(5, 48, 63.33, '12:49 PM', '13/09/2018', null, -85.7, 38.2, 48.6514469, 14.4161297)," +
                    "(43, 93, 35.42, '12:23 PM', '07/06/2019', null, 111.305369, -6.9627021, 40.0982704, 139.9803194)," +
                    "(54, 7, 78.57, '12:04 PM', '17/03/2019', null, 124.759938, 8.5067807, -16.2976181, -68.533658)," +
                    "(58, 25, 54.53, '1:59 PM', '09/12/2018', null, 120.556005, 31.875572, 18.3637867, 103.651666)," +
                    "(1, 92, 20.17, '4:14 PM', '07/03/2019', null, 73.6830521, 42.8297548, 43.0926393, 0.7978655)," +
                    "(28, 79, 49.69, '4:10 PM', '26/11/2018', null, '123.8661', '-9.7732', 47.59585, 126.100213)," +
                    "(8, 60, 34.08, '1:03 AM', '30/06/2019', null, 121.7587348, 16.9334683, -6.2746751, -79.6080967)," +
                    "(35, 95, 28.23, '3:18 PM', '27/04/2019', null, -8.6291241, 40.6816244, 46.013979, 83.663731)," +
                    "(8, 58, 91.17, '3:36 PM', '19/03/2019', null, '35.46756', '31.95069', 51.1691046, 18.3941226)," +
                    "(43, 50, 0.28, '4:40 AM', '19/09/2018', null, 21.6211703, 41.9844806, 34.477861, 110.084789)," +
                    "(58, 89, 66.77, '11:16 PM', '28/07/2018', null, 106.7474013, -6.3472173, 34.050751, -118.3841777)," +
                    "(49, 57, 48.51, '9:44 AM', '03/06/2019', null, 140.3006201, 38.1663943, 50.1454119, 15.7904531)," +
                    "(37, 49, 37.84, '6:43 AM', '16/12/2018', null, 116.317558, 39.975999, 45.2028539, 19.2482014)," +
                    "(48, 58, 90.52, '3:49 PM', '06/12/2018', null, 19.7398213, 40.7511134, 12.5050457, 123.4144449)," +
                    "(9, 91, 9.59, '9:54 PM', '14/02/2019', null, -77.1470783, 18.1254083, 49.0386312, 16.5942897)," +
                    "(29, 2, 2.71, '9:05 PM', '04/11/2018', null, -99.1825465, 19.268942, 48.8489882, 16.0910758)," +
                    "(57, 79, 66.3, '6:00 AM', '04/01/2019', null, 44.0127731, 13.4597697, 44.0271757, 44.0430569)," +
                    "(3, 88, 11.65, '3:56 AM', '16/07/2018', null, 17.8864717, 59.2527176, 42.4576548, -2.4407498)," +
                    "(6, 39, 40.42, '7:21 AM', '14/02/2019', null, 135.0631263, 48.4935627, 26.393274, 118.502373)," +
                    "(59, 92, 22.19, '2:02 PM', '31/03/2019', null, 17.5873433, 49.2210669, 37.9331181, 102.6293927)," +
                    "(46, 84, 82.74, '5:27 AM', '27/04/2019', null, 16.7664943, 49.6271732, 49.5477597, 5.8532859)," +
                    "(18, 97, 34.07, '2:06 PM', '14/02/2019', null, -48.4609448, -1.3964218, 31.288153, 114.618236)," +
                    "(52, 61, 41.16, '11:37 AM', '07/01/2019', null, 18.1662713, 52.0697452, 49.070618, 17.9723651)," +
                    "(60, 28, 97.12, '8:50 PM', '06/01/2019', null, 129.245343, 48.10791, 48.7925732, -79.201011)," +
                    "(16, 13, 72.44, '7:53 AM', '18/01/2019', null, 30.3865325, 59.924661, -12.1579355, 44.4146374)," +
                    "(15, 7, 23.07, '7:35 PM', '10/01/2019', null, 108.024301, -6.6047761, 60.3435461, 59.9882605)," +
                    "(5, 67, 86.17, '11:25 AM', '26/04/2019', null, 107.9276672, -6.7796462, '31.67361', '35.25662')," +
                    "(24, 5, 68.69, '1:25 AM', '13/08/2018', null, 107.6626743, -6.866705, -4.8768346, -80.9732924)," +
                    "(23, 47, 62.9, '9:23 PM', '17/05/2019', null, 115.771093, 28.159141, 13.9013242, -87.1996084)," +
                    "(34, 33, 21.87, '8:03 AM', '01/05/2019', null, 116.0761121, 5.9840985, 43.6158299, 13.518915)," +
                    "(38, 48, 71.16, '8:32 AM', '05/07/2018', null, 2.5726619, 45.494149, '41.535', '60.34556')," +
                    "(51, 51, 50.23, '11:01 AM', '11/04/2019', null, 116.632891, 27.252847, -12.0169195, -75.2799884)," +
                    "(22, 14, 62.37, '3:40 PM', '08/07/2018', null, 121.4379074, 31.2028365, 40.6898292, 22.858794)," +
                    "(27, 62, 41.15, '1:18 PM', '26/10/2018', null, 106.4877072, 9.6311842, 32.883468, 115.806942)," +
                    "(41, 72, 64.91, '10:27 PM', '04/08/2018', null, 89.086753, 55.620647, -1.0762009, -46.5547852)," +
                    "(22, 69, 22.54, '12:34 AM', '13/03/2019', null, 15.6105128, 60.5734263, 15.1955811, 104.8304807)," +
                    "(21, 20, 27.56, '8:46 PM', '29/01/2019', null, 13.1391427, 58.4986113, 11.7315405, 28.3578828)," +
                    "(23, 12, 46.5, '2:41 AM', '19/05/2019', null, 132.4282484, 34.9442746, 47.7489727, 7.3507066)," +
                    "(53, 22, 58.71, '11:34 PM', '11/11/2018', null, -81.5322149, 23.0303395, -8.4324536, 123.1618753)," +
                    "(39, 34, 86.79, '3:28 AM', '03/10/2018', null, 110.3426018, 1.4871144, 28.6559273, 121.2198521)," +
                    "(18, 7, 7.55, '8:49 PM', '12/09/2018', null, -70.265129, -15.24381, 15.0712647, 39.0450242)," +
                    "(23, 6, 20.28, '10:27 AM', '18/07/2018', null, 110.632006, 36.759507, -7.0655466, -35.3246769)," +
                    "(45, 2, 49.42, '8:39 AM', '31/01/2019', null, 44.2085511, 43.6971283, '-9.7366', '124.4852')," +
                    "(38, 29, 73.42, '1:14 PM', '22/04/2019', null, 10.5390217, 52.2802311, -6.352044, 106.628299)," +
                    "(45, 38, 37.91, '8:07 PM', '11/08/2018', null, -73.393617, 7.761063, 28.189633, 112.986397)," +
                    "(53, 27, 75.84, '12:02 AM', '11/12/2018', null, 110.7167165, -7.6155876, 37.499972, 105.196902)," +
                    "(30, 83, 16.92, '10:16 PM', '01/02/2019', null, 118.886931, 42.25785, 37.0654883, 21.6379278)," +
                    "(37, 43, 39.52, '7:47 AM', '11/09/2018', null, '111.0566', '-6.5951', -9.1015826, 160.147069)," +
                    "(26, 6, 13.74, '1:48 PM', '03/04/2019', null, 23.7536323, 38.0426062, 27.8169, 99.705419)," +
                    "(43, 59, 94.95, '2:05 PM', '21/07/2018', null, 14.1351449, 66.3110843, 59.4874462, 17.8147061)," +
                    "(57, 87, 40.75, '12:21 PM', '08/01/2019', null, '127.1', '36.12944', 7.138166, 7.6697323)," +
                    "(53, 45, 18.71, '5:57 PM', '20/01/2019', null, 158.2083333, 6.9641667, 52.5139674, 23.3517478)," +
                    "(10, 5, 42.32, '8:00 PM', '28/02/2019', null, 43.7732736, 40.765846, 35.897702, 114.184427)," +
                    "(18, 4, 72.97, '11:35 PM', '01/01/2019', null, 117.5532603, 8.9215342, 34.470812, 103.982523)," +
                    "(48, 16, 98.99, '11:51 PM', '05/02/2019', null, 102.236216, 6.129226, 52.1899235, 18.8632124)," +
                    "(40, 29, 80.77, '6:17 PM', '18/11/2018', null, 120.2343758, 33.7841996, 56.8965251, 14.8077914)," +
                    "(39, 15, 92.23, '6:04 PM', '19/12/2018', null, 22.3558193, 38.1481953, -9.3588996, 119.9162276)," +
                    "(43, 46, 50.06, '1:32 PM', '09/10/2018', null, 101.2804075, 6.5411018, 29.334318, 117.001279)," +
                    "(8, 47, 31.23, '11:39 PM', '07/10/2018', null, 114.478864, 38.093189, 55.796264, 37.719368)," +
                    "(44, 42, 98.41, '5:35 PM', '02/04/2019', null, 100.993355, 26.159433, 54.333333, 32.466667)," +
                    "(24, 87, 80.31, '8:59 PM', '11/08/2018', null, 1.9038837, 43.0429124, 32.010214, 119.606439)," +
                    "(43, 31, 89.51, '10:33 AM', '29/01/2019', null, 112.938814, 28.228209, 55.601379, 36.4655267)," +
                    "(10, 4, 91.97, '5:54 AM', '16/03/2019', null, 41.3625021, 44.7656213, 14.5820308, -91.6242154)," +
                    "(15, 65, 13.9, '8:36 AM', '20/02/2019', null, '37.73278', '36.67006', 44.7016194, 4.5805038)," +
                    "(58, 68, 77.72, '1:10 PM', '14/03/2019', null, 109.2396366, -7.4242782, 11.8042973, 15.8613233)," +
                    "(9, 3, 17.36, '4:31 PM', '16/04/2019', null, 174.9213435, -37.0555803, -29.2950409, -51.5008921)," +
                    "(4, 76, 59.54, '1:41 AM', '12/08/2018', null, -51.4264048, -23.4157731, -0.0411552, 109.3509465)," +
                    "(30, 95, 15.29, '4:54 AM', '29/05/2019', null, 127.253895, 35.891275, 27.0874564, 114.9042208)," +
                    "(11, 89, 50.83, '9:54 PM', '08/05/2019', null, 112.028866, 37.438101, 22.6594661, 105.9641124)," +
                    "(12, 68, 50.44, '10:59 AM', '30/04/2019', null, -77.8267218, 21.241735, 22.529403, 103.93935)," +
                    "(31, 31, 45.18, '5:45 PM', '10/02/2019', null, 115.451043, 24.086165, '28.3', '88.53333')," +
                    "(18, 61, 39.33, '5:34 AM', '15/01/2019', null, 21.46318, 52.2081565, 7.4240414, -6.0520363)," +
                    "(57, 9, 57.03, '4:38 AM', '24/12/2018', null, 25.7456852, -5.383454, 38.402951, -28.080155)," +
                    "(20, 22, 4.99, '7:10 AM', '12/06/2019', null, 122.5171185, 14.1893533, '-8.2916', '122.9193')," +
                    "(33, 52, 8.18, '3:28 PM', '26/10/2018', null, 111.1995479, -6.7216825, 37.510549, 118.325556)," +
                    "(7, 60, 55.02, '11:11 PM', '05/02/2019', null, -51.4628097, -25.3907214, 50.0600037, 37.3822945)," +
                    "(8, 53, 52.81, '6:23 PM', '12/11/2018', null, -71.7147951, 10.6544509, 32.085484, 112.124371)," +
                    "(46, 71, 39.2, '10:05 PM', '09/06/2019', null, -75.275171, 10.444021, 1.5991751, 11.5759672)," +
                    "(22, 64, 94.84, '10:15 PM', '25/12/2018', null, 7.950014, 49.031645, 13.7315145, 100.5692601)," +
                    "(40, 28, 92.39, '11:54 PM', '17/02/2019', null, 112.851831, 35.490701, 13.7634469, 123.055727)," +
                    "(21, 96, 39.82, '3:04 AM', '14/02/2019', null, -8.3802434, 41.286107, 48.858575, 17.0286982)," +
                    "(3, 54, 85.36, '10:20 AM', '07/02/2019', null, 98.9797467, 3.5674986, 11.9528558, 121.9311073)," +
                    "(33, 68, 68.61, '6:23 AM', '11/05/2019', null, 103.1517902, -0.3221142, 47.853778, 128.84075)," +
                    "(38, 79, 18.85, '11:29 PM', '22/12/2018', null, 165.2386673, -20.7795586, 13.7464901, 100.5129096)," +
                    "(9, 58, 26.3, '3:07 AM', '27/06/2019', null, 114.482606, 29.605376, -8.053523, 111.8598395)," +
                    "(45, 47, 65.23, '4:00 PM', '22/08/2018', null, 49.8739267, 11.4720137, 49.9731106, 32.9873888)," +
                    "(31, 70, 77.67, '3:19 AM', '10/10/2018', null, 112.3933836, -8.0464521, 18.8104433, -71.2236939)," +
                    "(50, 68, 3.86, '5:18 PM', '01/05/2019', null, 25.4333251, 36.3384589, 42.2152585, 20.7414739)," +
                    "(46, 90, 81.97, '11:05 AM', '24/09/2018', null, '114.0409', '-7.7474', 53.1620102, 16.6118082)," +
                    "(27, 55, 22.78, '1:26 PM', '16/12/2018', null, 120.5550729, 40.798124, 35.476788, 110.442846)," +
                    "(34, 70, 96.54, '5:27 AM', '28/01/2019', null, 111.7197315, -7.1929741, -13.3964033, -72.0514291)," +
                    "(48, 49, 1.0, '4:09 AM', '22/04/2019', null, 23.7275388, 37.9838096, 29.0554502, 114.1574404)," +
                    "(23, 7, 33.72, '12:49 AM', '28/04/2019', null, 120.989116, 14.6602787, 20.995464, 106.3346061)," +
                    "(56, 46, 32.65, '9:44 AM', '03/11/2018', null, 82.353656, 44.840524, 7.743026, 124.210303)," +
                    "(21, 60, 19.26, '2:32 PM', '29/05/2019', null, 108.487368, 22.75839, -6.3385652, 106.9447146)," +
                    "(23, 5, 23.4, '4:29 PM', '02/02/2019', null, 124.8833084, 8.9951264, 48.6769923, 22.3927591)," +
                    "(43, 24, 65.66, '1:58 PM', '19/04/2019', null, -104.1175578, 24.4432479, -7.6492089, 111.4395128)," +
                    "(57, 77, 3.85, '2:28 PM', '04/06/2019', null, '45.83488', '13.83783', 14.0720691, -90.418548)," +
                    "(19, 38, 27.87, '1:19 PM', '10/08/2018', null, -89.430685, 14.7641115, -7.7480174, -37.6346628)," +
                    "(34, 49, 29.61, '8:35 AM', '08/10/2018', null, 44.4087348, 14.5455447, 45.1839187, 18.8237103)," +
                    "(10, 35, 18.19, '2:20 PM', '12/04/2019', null, 114.786182, 26.458254, 28.074649, 119.141473)," +
                    "(26, 20, 21.0, '7:13 PM', '18/12/2018', null, 13.8214707, 49.275632, 37.7621762, -25.5651478)," +
                    "(50, 72, 46.41, '1:20 PM', '28/08/2018', null, 5.997642, 43.1342238, 33.189192, 120.103473)," +
                    "(34, 5, 9.15, '6:51 AM', '24/06/2019', null, 35.6629038, 32.3858505, 42.0416939, 19.94522)," +
                    "(2, 1, 7.28, '10:41 PM', '20/11/2018', null, 17.2602307, 51.1529103, 45.3725657, 20.2841743)," +
                    "(40, 95, 70.79, '6:51 PM', '28/03/2019', null, 2.2582125, 48.8466523, 22.6113591, 120.3493158)," +
                    "(51, 90, 60.58, '4:23 PM', '04/11/2018', null, 112.1966734, -6.987872, 37.9337124, 139.3844092)," +
                    "(36, 76, 92.63, '7:03 AM', '26/08/2018', null, 113.190869, 22.613406, 26.242301, 105.943312)," +
                    "(4, 86, 67.86, '6:20 PM', '18/05/2019', null, 35.6423527, -10.6462694, 14.6927846, 121.1100951)," +
                    "(9, 23, 5.98, '8:20 PM', '23/04/2019', null, 17.8317085, 43.7356628, 43.4433559, 16.6929175)," +
                    "(43, 87, 66.61, '9:37 PM', '17/03/2019', null, -65.0008956, -31.7306526, 41.2529921, -7.9536382)," +
                    "(15, 84, 95.91, '12:19 PM', '17/05/2019', null, -71.893158, -13.54918, 13.760522, -88.2680172)," +
                    "(34, 87, 52.23, '3:35 PM', '28/01/2019', null, 101.207047, 16.0045432, '51.46681', '-109.16818')," +
                    "(50, 95, 24.33, '6:26 AM', '29/03/2019', null, '105.9693', '-6.3965', 32.9454027, -117.2399868)," +
                    "(2, 97, 16.96, '7:33 PM', '16/04/2019', null, 121.49591, 29.990745, 45.7140258, 20.7713965)," +
                    "(38, 60, 72.81, '12:55 AM', '10/05/2019', null, 108.7927679, -7.0636577, 50.7140155, 14.7099785)," +
                    "(59, 15, 40.58, '4:30 PM', '23/03/2019', null, 6.0255738, 12.3258467, 42.5246357, 87.5395855)," +
                    "(31, 30, 75.74, '10:56 AM', '30/11/2018', null, 118.403786, 29.866917, 39.726929, 116.341395)," +
                    "(50, 81, 84.69, '3:27 AM', '05/06/2019', null, 31.5040656, 30.5765383, 27.098536, 114.972769)," +
                    "(4, 81, 1.08, '8:38 AM', '08/07/2018', null, -76.4302771, 20.3650844, 30.999016, 102.363193)," +
                    "(23, 76, 23.44, '11:03 AM', '15/08/2018', null, 26.4565448, 39.0390133, 23.1351485, -82.3695988)," +
                    "(51, 61, 29.18, '9:52 AM', '17/07/2018', null, -57.0479481, -34.3583236, -0.5107264, -78.5717631)," +
                    "(53, 56, 0.08, '7:06 PM', '30/08/2018', null, 101.738528, 26.497765, 19.6786236, -101.1808488)," +
                    "(13, 48, 34.98, '7:03 AM', '16/06/2019', null, 121.0228804, 14.5919106, 27.607731, 120.558665)," +
                    "(4, 68, 74.86, '10:24 AM', '19/09/2018', null, 21.3924958, 50.0919002, 49.116169, 6.1807997)," +
                    "(38, 61, 79.91, '1:20 AM', '10/02/2019', null, 107.0219496, 47.6126, 6.0343516, 124.5217429)," +
                    "(55, 55, 4.19, '9:35 AM', '07/12/2018', null, 40.7334111, 58.8144355, 37.5743169, 140.0611908)," +
                    "(4, 94, 67.42, '10:19 AM', '11/09/2018', null, 113.300129, 40.076762, 50.9811092, 17.0357954)," +
                    "(43, 29, 7.11, '12:30 PM', '23/10/2018', null, '35.23227', '31.57112', 35.375873, 139.1671308)," +
                    "(56, 26, 69.53, '12:18 PM', '05/11/2018', null, 47.818038, -22.817412, -25.692754, 29.4306557)," +
                    "(9, 45, 80.46, '1:08 PM', '30/11/2018', null, -8.9480659, 53.940892, 49.858677, 18.9770856)," +
                    "(21, 75, 52.1, '7:39 AM', '07/06/2019', null, 110.112629, 32.809026, 51.0935791, -115.3508611)," +
                    "(6, 83, 35.17, '9:33 AM', '16/07/2018', null, -121.47, 38.57, -31.9505269, 115.8604572)," +
                    "(11, 27, 61.54, '7:56 AM', '23/01/2019', null, 111.013556, 21.514163, 63.8532929, 23.0832748)," +
                    "(34, 74, 84.14, '4:24 PM', '20/04/2019', null, 112.746435, -7.240959, 43.6296613, 39.705727)," +
                    "(10, 16, 55.65, '10:04 AM', '10/11/2018', null, 18.3961325, 53.2122406, 49.839683, 24.029717)," +
                    "(51, 8, 60.56, '12:12 AM', '20/05/2019', null, 16.0518078, 45.0510883, 40.8673649, 20.3621596)," +
                    "(48, 88, 71.45, '5:02 PM', '18/05/2019', null, -70.4059336, 18.9331158, -34.9001596, -58.0352133)," +
                    "(57, 97, 70.78, '3:03 PM', '19/05/2019', null, 104.1029064, 30.6031858, 40.657978, 109.840313)," +
                    "(8, 89, 40.94, '12:30 PM', '09/07/2018', null, -71.2106242, -32.7910792, 37.540419, 120.787999)," +
                    "(29, 16, 79.71, '8:57 AM', '29/04/2019', null, -90.8066537, 14.6214687, 24.363492, 117.309946)," +
                    "(29, 12, 33.34, '8:08 AM', '01/06/2019', null, 45.4197638, 43.2588087, 30.9606622, 61.8526229)," +
                    "(22, 74, 80.72, '6:31 PM', '02/03/2019', null, 20.9876839, 39.1582421, -13.6667285, -73.3487639)," +
                    "(22, 32, 27.26, '2:01 PM', '29/03/2019', null, 76.8512485, 43.2220146, 40.3706555, 47.1378909)," +
                    "(43, 37, 34.31, '5:50 PM', '24/02/2019', null, -64.2726889, -31.3203573, 59.3559924, 26.9669311)," +
                    "(57, 92, 83.33, '12:49 AM', '21/10/2018', null, 31.2067851, 30.1762897, '55.0218', '82.202')," +
                    "(30, 21, 12.57, '12:56 PM', '29/01/2019', null, 111.759598, -7.221562, 21.8405365, -78.7589558)," +
                    "(5, 93, 78.58, '9:31 PM', '11/10/2018', null, 115.098698, 35.288456, -18.4315276, -39.9322068)," +
                    "(44, 84, 28.33, '2:20 AM', '02/07/2018', null, 105.592898, 30.532847, 28.159141, 115.771093)," +
                    "(57, 40, 8.38, '5:30 PM', '17/02/2019', null, -1.6807958, 48.1504081, 44.2643449, 43.6369585)," +
                    "(40, 27, 97.41, '5:02 AM', '16/07/2018', null, 2.3764372, 43.4899263, 34.477861, 110.084789)," +
                    "(38, 9, 32.07, '5:02 AM', '18/11/2018', null, 116.805796, 35.758786, 22.163704, 110.671258)," +
                    "(39, 11, 43.59, '2:02 PM', '13/07/2018', null, 27.6952713, -26.1026584, 23.883531, 110.666614)," +
                    "(54, 6, 2.57, '8:18 AM', '04/07/2018', null, 40.6134152, 55.4563422, 13.7946714, 5.2527741)," +
                    "(1, 50, 69.91, '3:17 PM', '03/04/2019', null, 120.589457, 30.145729, 0.2827307, 34.7518631)," +
                    "(58, 40, 29.99, '6:42 AM', '29/09/2018', null, 120.3656906, 15.9866126, 44.8310092, 37.5460072)," +
                    "(20, 98, 31.06, '2:22 PM', '02/12/2018', null, 47.6381357, -21.3032134, 49.2914367, 16.5787482)," +
                    "(33, 57, 74.26, '4:08 AM', '09/02/2019', null, 18.0760798, 59.3125021, -7.1272731, 108.6828838)," +
                    "(47, 98, 97.64, '6:27 PM', '03/02/2019', null, '14.1539', '9.7639', 21.3785203, 106.0875326);";

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_CAR);
        db.execSQL(SQL_CREATE_ENTRIES_TRIP);
        db.execSQL(SQL_CREATE_ENTRIES_STATION);
        db.execSQL(SQL_INSERT_USER);
        db.execSQL(SQL_INSERT_CAR);
        db.execSQL(SQL_INSERT_TRIP);
        Log.d("MyDB","onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_CAR);
        db.execSQL(SQL_DELETE_ENTRIES_TRIP);
        db.execSQL(SQL_DELETE_ENTRIES_STATION);
        Log.d("MyDB","OnUpgrade");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDB","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }
}
