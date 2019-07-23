package com.example.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
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
                    TableEntry.CAR_COLUMN_NAME_COORDX + " NUMERIC, " +
                    TableEntry.CAR_COLUMN_NAME_COORDY + " NUMERIC)";

    private static final String SQL_CREATE_ENTRIES_TRIP =
            "CREATE TABLE " + TableEntry.TRIP_TABLE_NAME + " (" +
                    TableEntry.TRIP_COLUMN_NAME_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TableEntry.TRIP_COLUMN_NAME_CAR_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_USER_ID + " INTEGER," +
                    TableEntry.TRIP_COLUMN_NAME_AMOUNT + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_KMS_RUN_FOR_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_TIME_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_DATE_OF_TRIP + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_STARTINGY + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGX + " NUMERIC NOT NULL," +
                    TableEntry.TRIP_COLUMN_NAME_ENDINGY + " NUMERIC NOT NULL," +
                    "FOREIGN KEY(carId) REFERENCES Car(carId)," +
                    "FOREIGN KEY(userId) REFERENCES User(userId))";

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + TableEntry.USER_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_CAR =
            "DROP TABLE IF EXISTS " + TableEntry.CAR_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_TRIP =
            "DROP TABLE IF EXISTS " + TableEntry.TRIP_TABLE_NAME;

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
            "insert into Car (costOfRunning, seats, doors, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inService, coordX, coordY) values ('2.51', 2, 2, 20, 199291, 8641, 'Escape', '6d958d', 'true', 'true', 5.5126065, 95.7932008)," +
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

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_CAR);
        db.execSQL(SQL_CREATE_ENTRIES_TRIP);
        db.execSQL(SQL_INSERT_USER);
        db.execSQL(SQL_INSERT_CAR);
        Log.d("MyDB","onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_CAR);
        db.execSQL(SQL_DELETE_ENTRIES_TRIP);
        Log.d("MyDB","OnUpgrade");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDB","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }
}
