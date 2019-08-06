package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.awt.font.TextAttribute;


public class MyDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
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
                            "('nburston1d', 'hgVBkuMZ7oKJ', 'Nelle', 'Burston', 'nburston1d@google.com', '762-633-3773', '3596 Towne Way', 'Páno Polemídia', 'Cyprus', '01/01/1968', 'switch', 'true');";


    private static final String SQL_INSERT_CAR =
            "insert into Car (costOfRunning, seats, doors, serviceTime, kmsRun, kmsSinceLastService, vehicleType, licensePlate, inUse, inActiveService, coordX, coordY, inStation) " +
                    "values (1.67, 5, 4, 9, 182665, 12312, 'Small Car', '8ff6e8', 'true', 'true', 49.2316605, -123.0231751, 'false')," +
                    "(4.3, 2, 2, 5, 120954, 13265, 'Van', 'd14ff9', 'false', 'true', 49.2329242, -123.0235498, 'false')," +
                    "(4.44, 8, 4, 16, 57169, 296, 'CRV Car', '6b0a52', 'false', 'true', 47.315723, -121.3887673, 'true')," +
                    "(3.5, 2, 2, 12, 175218, 9275, 'CRV Car', '6d2f24', 'true', 'true', 47.315723, -121.3887673, 'true')," +
                    "(3.91, 8, 4, 14, 1373, 14977, 'Van', 'c9aa37', 'false', 'true', 49.232632, -123.0228921, 'false')," +
                    "(1.39, 2, 4, 16, 181218, 5356, 'Van', 'e8e687', 'false', 'false', 49.232402, -123.0237415, 'false')," +
                    "(1.13, 2, 4, 1, 78804, 7936, 'Small Car', 'ce3253', 'false', 'false', 49.231408, -123.0224137, 'false')," +
                    "(1.3, 2, 2, 15, 90922, 13257, 'CRV Car', '8f4d1e', 'true', 'true', 47.315723, -121.3887673, 'true')," +
                    "(2.98, 2, 4, 10, 107494, 8177, 'Small Car', 'd084be', 'false', 'false', 47.315723, -121.3887673, 'true')," +
                    "(3.47, 2, 4, 20, 157009, 1860, 'Small Car', '513150', 'false', 'true', 47.315723, -121.3887673, 'true')," +
                    "(2.93, 2, 4, 4, 180577, 2169, 'CRV Car', '1498a0', 'true', 'false', 49.231155, -123.0228072, 'false')," +
                    "(4.35, 5, 2, 8, 44276, 14921, 'CRV Car', 'c5d5ef', 'false', 'true', 49.232743, -123.0221912, 'false')," +
                    "(4.82, 8, 4, 14, 154138, 13520, 'Van', 'c2a623', 'true', 'true', 47.315723, -121.3887673, 'true')," +
                    "(2.22, 5, 4, 3, 138275, 1146, 'Van', '2103a8', 'false', 'false', 49.2317558, -123.02214, 'false')," +
                    "(4.55, 5, 4, 14, 54709, 1182, 'Small Car', '359582', 'false', 'false', 47.315723, -121.3887673, 'true')," +
                    "(1.1, 8, 2, 19, 103758, 9184, 'CRV Car', '087080', 'false', 'false', 49.2327451, -123.0221811, 'false')," +
                    "(1.37, 5, 2, 8, 16172, 14215, 'Van', '9f50b8', 'true', 'true', 47.315723, -121.3887673, 'true')," +
                    "(2.46, 5, 2, 9, 73044, 3422, 'Small Car', '68cec3', 'true', 'true', 49.2311143, -123.0239204, 'false')," +
                    "(1.17, 5, 2, 18, 143309, 5273, 'Van', 'db3231', 'true', 'true', 49.2317892, -123.02353, 'false')," +
                    "(4.66, 5, 4, 2, 53152, 9127, 'CRV Car', '738965', 'false', 'true', 47.315723, -121.3887673, 'true')," +
                    "(2.18, 2, 4, 16, 148379, 12748, 'CRV Car', 'fc0785', 'true', 'false', 47.315723, -121.3887673, 'true')," +
                    "(1.73, 8, 2, 19, 125023, 372, 'Small Car', 'c0896b', 'true', 'true', 49.2318757, -123.0225132, 'false')," +
                    "(2.86, 5, 2, 9, 50497, 2322, 'Van', '2a73cc', 'true', 'false', 47.5776813, -121.7745689, 'true')," +
                    "(2.22, 8, 4, 16, 199150, 14541, 'Small Car', '20f36b', 'false', 'true', 49.2322391, -123.0221565, 'false')," +
                    "(1.94, 8, 4, 8, 72199, 2195, 'CRV Car', '513580', 'false', 'false', 47.5776813, -121.7745689, 'true')," +
                    "(1.47, 5, 4, 2, 98345, 10130, 'Van', 'ae795f', 'false', 'false', 48.3978238, -122.1406025, 'true')," +
                    "(3.01, 8, 2, 15, 76390, 11897, 'Small Car', '2a58ff', 'true', 'true', 49.2324922, -123.0232374, 'false')," +
                    "(2.47, 2, 4, 3, 89066, 9869, 'CRV Car', '5f312f', 'true', 'false', 48.3978238, -122.1406025, 'true')," +
                    "(2.42, 5, 4, 20, 163834, 2466, 'Small Car', 'ba5ae6', 'true', 'true', 48.3978238, -122.1406025, 'true')," +
                    "(3.28, 8, 4, 17, 185442, 10583, 'CRV Car', 'ecd6b7', 'true', 'false', 48.3978238, -122.1406025, 'true')";

    private static final String SQL_INSERT_TRIP =

            "insert into Trip (carId, userId, kmsRunForTrip, timeOfTrip, dateOfTrip, amount, startingX, startingY, endingX, endingY)" +
                    "values (8, 43, 57.85, '9:03 PM', '3/4/2019', null, 48.9710843, -122.9477, 48.9474019, -122.8680301)," +
                    "(8, 24, 31.44, '10:46 PM', '10/28/2018', null, 48.3736019, -122.8360774, 48.9085678, -122.8923337)," +
                    "(16, 24, 82.57, '11:14 AM', '5/31/2019', null, 48.0056084, -122.4660739, 48.0798783, -122.8614577)," +
                    "(13, 37, 46.86, '4:15 AM', '12/22/2018', null, 48.2513806, -122.2672577, 48.7751795, -122.6426564)," +
                    "(4, 19, 1.16, '5:58 PM', '4/17/2019', null, 48.7958486, -122.4512841, 48.6195827, -122.5039504)," +
                    "(6, 49, 97.23, '9:13 AM', '2/14/2019', null, 48.7329231, -122.991657, 48.9629291, -122.0359991)," +
                    "(14, 25, 48.77, '10:40 PM', '4/28/2019', null, 48.4195767, -122.2067894, 48.5653045, -122.7385689)," +
                    "(5, 27, 50.92, '11:19 PM', '11/19/2018', null, 48.2356714, -122.9720658, 48.7693274, -122.6494871)," +
                    "(14, 33, 10.46, '2:44 AM', '6/28/2019', null, 48.9244427, -122.9411521, 48.0645409, -122.5376956)," +
                    "(17, 46, 50.63, '4:08 AM', '3/29/2019', null, 48.4899968, -122.0230948, 48.6655381, -122.2329594)," +
                    "(28, 42, 2.37, '6:02 PM', '12/2/2018', null, 48.0342553, -122.8893653, 48.0720299, -122.782758)," +
                    "(17, 22, 69.41, '7:00 AM', '8/23/2018', null, 48.9687739, -122.4343457, 48.0480594, -122.2054574)," +
                    "(11, 45, 83.32, '10:16 AM', '8/25/2018', null, 48.1632303, -122.651432, 48.8742201, -122.766574)," +
                    "(8, 39, 58.5, '1:55 AM', '4/15/2019', null, 48.0218459, -122.8314644, 48.8758361, -122.1334969)," +
                    "(15, 22, 64.84, '9:37 PM', '9/11/2018', null, 48.0641526, -122.8579321, 48.0836384, -122.7751753)," +
                    "(18, 30, 20.54, '8:57 PM', '5/20/2019', null, 48.9901979, -122.4459511, 48.9603769, -122.8594137)," +
                    "(4, 11, 60.38, '2:55 AM', '5/25/2019', null, 48.1268536, -122.84388, 48.7240422, -122.6239226)," +
                    "(20, 37, 4.64, '6:28 PM', '1/3/2019', null, 48.8483906, -122.4089115, 48.7252659, -122.0338993)," +
                    "(16, 7, 56.34, '7:45 AM', '4/10/2019', null, 48.974068, -122.2854058, 48.9123377, -122.2028318)," +
                    "(25, 18, 34.03, '3:41 PM', '7/8/2019', null, 48.3941803, -122.2248264, 48.9967361, -122.7638508)," +
                    "(19, 18, 51.79, '3:32 PM', '4/12/2019', null, 48.4277682, -122.4365809, 48.7452763, -122.0944008)," +
                    "(6, 24, 18.3, '8:50 PM', '10/18/2018', null, 48.8426514, -122.70616, 48.8354992, -122.3939577)," +
                    "(25, 23, 14.39, '1:00 PM', '12/14/2018', null, 48.8765235, -122.4959223, 48.5398832, -122.3591795)," +
                    "(20, 34, 39.98, '6:57 AM', '10/14/2018', null, 48.2079829, -122.403661, 48.1052643, -122.1607752)," +
                    "(25, 15, 70.03, '12:52 PM', '12/18/2018', null, 48.1164629, -122.521777, 48.3543368, -122.1254545)," +
                    "(8, 42, 66.75, '11:11 AM', '11/13/2018', null, 48.6987547, -122.9908467, 48.2907725, -122.3226847)," +
                    "(2, 9, 23.49, '9:40 PM', '10/7/2018', null, 48.4659899, -122.5087684, 48.8313868, -122.7909595)," +
                    "(11, 32, 50.23, '1:26 PM', '12/19/2018', null, 48.7186573, -122.8622989, 48.3564735, -122.6151281)," +
                    "(14, 29, 33.37, '1:45 PM', '3/20/2019', null, 48.0378458, -122.0369655, 48.3810842, -122.8836961)," +
                    "(5, 43, 60.78, '9:53 AM', '11/27/2018', null, 48.9916911, -122.1075909, 48.3464623, -122.6594237)," +
                    "(3, 45, 92.28, '3:20 PM', '6/27/2019', null, 48.4792547, -122.4344222, 48.6762239, -122.9820301)," +
                    "(25, 20, 90.13, '6:36 AM', '2/8/2019', null, 48.9673475, -122.9559496, 48.1511356, -122.4872638)," +
                    "(16, 29, 90.3, '12:42 PM', '7/22/2019', null, 48.7179118, -122.1726006, 48.6012731, -122.054431)," +
                    "(10, 16, 78.66, '3:38 AM', '4/2/2019', null, 48.21796, -122.6830782, 48.3606693, -122.0342789)," +
                    "(15, 18, 81.38, '11:14 PM', '2/10/2019', null, 48.289633, -122.1554855, 48.9759794, -122.7954459)," +
                    "(22, 1, 50.41, '4:47 AM', '11/11/2018', null, 48.3728745, -122.617196, 48.6081812, -122.2372411)," +
                    "(15, 16, 50.28, '11:48 AM', '9/24/2018', null, 48.5161634, -122.736362, 48.4521734, -122.352335)," +
                    "(23, 9, 64.55, '10:58 PM', '5/18/2019', null, 48.229046, -122.9199769, 48.7385496, -122.5805928)," +
                    "(12, 25, 78.32, '6:49 AM', '2/19/2019', null, 48.5222672, -122.0436967, 48.6084311, -122.8204591)," +
                    "(30, 10, 1.59, '8:41 PM', '2/10/2019', null, 48.0340405, -122.4129299, 48.7732946, -122.4843238)," +
                    "(19, 36, 74.07, '2:45 PM', '11/24/2018', null, 48.8101619, -122.7986797, 48.5867636, -122.5118396)," +
                    "(1, 3, 24.79, '9:20 PM', '8/29/2018', null, 48.8187063, -122.669327, 48.5590222, -122.2234316)," +
                    "(13, 23, 93.94, '5:39 AM', '5/27/2019', null, 48.8883432, -122.1437951, 48.1352556, -122.6213343)," +
                    "(24, 40, 12.79, '12:05 AM', '7/31/2019', null, 48.7488301, -122.7391097, 48.5972244, -122.6939935)," +
                    "(28, 42, 3.33, '7:02 AM', '11/17/2018', null, 48.4079868, -122.5332556, 48.2051837, -122.938544)," +
                    "(5, 10, 68.13, '5:10 PM', '5/30/2019', null, 48.2680664, -122.926621, 48.9254231, -122.6825712)," +
                    "(9, 7, 91.27, '6:38 AM', '4/2/2019', null, 48.5967945, -122.5311585, 48.59264, -122.5516455)," +
                    "(7, 23, 50.98, '3:56 AM', '4/17/2019', null, 48.1236463, -122.8352012, 48.9301627, -122.1124993)," +
                    "(23, 14, 20.32, '9:15 PM', '11/27/2018', null, 48.5300328, -122.9865529, 48.9853539, -122.3960196)," +
                    "(1, 16, 10.0, '10:30 PM', '3/6/2019', null, 48.3019137, -122.531177, 48.1427927, -122.0396853)," +
                    "(7, 49, 28.41, '12:36 PM', '8/11/2018', null, 48.3793167, -122.5970042, 48.6551354, -122.2775316)," +
                    "(15, 21, 5.62, '7:51 AM', '10/31/2018', null, 48.6568801, -122.868468, 48.230685, -122.7556634)," +
                    "(30, 9, 29.59, '3:22 PM', '1/2/2019', null, 48.4282622, -122.872534, 48.746717, -122.7792195)," +
                    "(17, 13, 84.71, '4:23 AM', '3/22/2019', null, 48.263592, -122.8409287, 48.7215208, -122.9160011)," +
                    "(21, 23, 27.05, '10:17 AM', '5/4/2019', null, 48.3427823, -122.1674041, 48.6329234, -122.2638921)," +
                    "(16, 43, 23.17, '2:40 AM', '10/16/2018', null, 48.872231, -122.1982337, 48.1350704, -122.7188966)," +
                    "(12, 32, 49.16, '5:41 PM', '4/19/2019', null, 48.181635, -122.7941575, 48.3407795, -122.8235644)," +
                    "(17, 35, 60.1, '11:00 PM', '7/3/2019', null, 48.1630433, -122.3031276, 48.2539048, -122.7989874)," +
                    "(13, 46, 68.82, '11:01 AM', '3/14/2019', null, 48.0137912, -122.3477422, 48.0144758, -122.2926959)," +
                    "(6, 32, 51.08, '10:26 PM', '3/26/2019', null, 48.326916, -122.9925537, 48.2758062, -122.4589184)," +
                    "(22, 20, 45.2, '3:34 AM', '3/19/2019', null, 48.6408108, -122.1916593, 48.3520832, -122.0290463)," +
                    "(30, 15, 62.92, '5:53 PM', '8/29/2018', null, 48.3898112, -122.4485656, 48.0557028, -122.7957534)," +
                    "(16, 36, 97.2, '2:33 AM', '10/2/2018', null, 48.1223064, -122.1291275, 48.9258079, -122.8951759)," +
                    "(2, 40, 93.08, '7:25 PM', '1/5/2019', null, 48.1029105, -122.9290983, 48.6544731, -122.0732675)," +
                    "(23, 22, 42.96, '7:09 PM', '8/13/2018', null, 48.2103634, -122.5047213, 48.9959856, -122.0750431)," +
                    "(24, 10, 91.43, '7:22 AM', '10/31/2018', null, 48.0431814, -122.6379268, 48.8238702, -122.4241948)," +
                    "(28, 11, 67.08, '4:02 PM', '6/24/2019', null, 48.1066414, -122.717847, 48.9561332, -122.6893609)," +
                    "(29, 25, 84.08, '7:08 AM', '8/7/2018', null, 48.3910549, -122.340669, 48.754374, -122.4473902)," +
                    "(22, 19, 65.67, '3:26 AM', '4/27/2019', null, 48.9403449, -122.5140377, 48.4851362, -122.7023294)," +
                    "(21, 31, 72.01, '5:02 AM', '1/10/2019', null, 48.0420853, -122.4202218, 48.3502424, -122.8497795)," +
                    "(12, 11, 76.04, '9:55 PM', '8/31/2018', null, 48.6289528, -122.9639028, 48.9063679, -122.9645916)," +
                    "(27, 27, 3.15, '1:33 PM', '12/12/2018', null, 48.5584212, -122.9380799, 48.3875759, -122.6860461)," +
                    "(4, 46, 31.85, '6:09 PM', '12/14/2018', null, 48.3524326, -122.6356545, 48.1425816, -122.8145192)," +
                    "(21, 48, 56.92, '4:46 PM', '8/9/2018', null, 48.6915286, -122.8408684, 48.9965072, -122.9180043)," +
                    "(8, 13, 12.15, '11:07 PM', '2/10/2019', null, 48.2023487, -122.1108515, 48.3726625, -122.3061248)," +
                    "(12, 25, 93.69, '1:25 PM', '6/2/2019', null, 48.0084529, -122.9513797, 48.6118303, -122.696449)," +
                    "(22, 15, 63.72, '4:53 AM', '10/16/2018', null, 48.6117394, -122.3544377, 48.1297015, -122.7023959)," +
                    "(10, 30, 12.02, '10:41 PM', '4/4/2019', null, 48.2685669, -122.4836059, 48.1254533, -122.8459978)," +
                    "(7, 47, 66.49, '9:36 AM', '9/4/2018', null, 48.5013679, -122.989825, 48.6078793, -122.3321302)," +
                    "(8, 43, 87.2, '6:20 PM', '3/4/2019', null, 48.3858143, -122.4502599, 48.1610664, -122.2500971)," +
                    "(3, 49, 55.85, '8:21 PM', '6/10/2019', null, 48.0650293, -122.8127671, 48.7864318, -122.0779511)," +
                    "(24, 50, 43.95, '3:11 AM', '9/6/2018', null, 48.893098, -122.9711132, 48.4802004, -122.2121187)," +
                    "(25, 16, 86.07, '4:58 AM', '8/2/2019', null, 48.8969695, -122.1719864, 48.3349102, -122.1603902)," +
                    "(5, 47, 24.16, '7:56 PM', '11/2/2018', null, 48.3436501, -122.8431734, 48.7765231, -122.022642)," +
                    "(6, 32, 77.51, '10:45 PM', '10/1/2018', null, 48.6145285, -122.6419732, 48.9492882, -122.4764504)," +
                    "(4, 47, 58.79, '7:00 AM', '10/22/2018', null, 48.3111211, -122.3113108, 48.7842714, -122.1078959)," +
                    "(29, 35, 84.34, '12:05 PM', '11/29/2018', null, 48.9890274, -122.375999, 48.4133545, -122.1644074)," +
                    "(28, 17, 36.32, '10:26 AM', '5/31/2019', null, 48.3121152, -122.8594526, 48.0773464, -122.1395531)," +
                    "(29, 15, 40.09, '11:12 AM', '5/30/2019', null, 48.0341851, -122.9274451, 48.8957795, -122.8490447)," +
                    "(22, 1, 68.94, '8:33 AM', '6/28/2019', null, 48.5426275, -122.3338351, 48.2397481, -122.463257)," +
                    "(22, 42, 72.29, '1:19 AM', '10/12/2018', null, 48.1073859, -122.9848139, 48.9874043, -122.224539)," +
                    "(11, 8, 44.22, '12:03 PM', '1/20/2019', null, 48.3647236, -122.5652895, 48.74524, -122.3566516)," +
                    "(27, 22, 14.51, '4:49 PM', '5/16/2019', null, 48.9820242, -122.0041275, 48.5520854, -122.8324311)," +
                    "(25, 43, 31.79, '1:27 AM', '12/30/2018', null, 48.276115, -122.6748876, 48.0104115, -122.0755863)," +
                    "(3, 11, 52.74, '9:49 AM', '3/18/2019', null, 48.6498577, -122.672469, 48.0150692, -122.4041873)," +
                    "(4, 15, 1.3, '12:12 PM', '2/20/2019', null, 48.7767052, -122.5063797, 48.8343819, -122.380682)," +
                    "(2, 7, 5.0, '11:45 AM', '7/4/2019', null, 48.019581, -122.6184541, 48.144491, -122.000192)," +
                    "(3, 7, 94.96, '10:04 PM', '11/18/2018', null, 48.6589232, -122.738578, 48.1792914, -122.9478849)," +
                    "(7, 35, 21.37, '1:18 PM', '2/12/2019', null, 48.649757, -122.0022716, 48.1072885, -122.4452155)," +
                    "(2, 5, 8.77, '7:52 PM', '6/17/2019', null, 48.7646, -122.5307338, 48.9687654, -122.0842938);";

    private static final String SQL_INSERT_STATION =
            "insert into Station (carsAtStation, locationX, locationY, stationActive) " +
                    "values (7, 48.3115451, -121.8644139, 'false')," +
                    "(6, 48.7029385, -122.4248229, 'false')," +
                    "(3, 48.3215987, -121.2812784, 'false')," +
                    "(1, 47.3618915, -122.0420793, 'false')," +
                    "(9, 47.7037701, -122.0975847, 'false')," +
                    "(7, 48.2406106, -122.3197809, 'false')," +
                    "(10, 47.315723, -121.3887673, 'true')," +
                    "(1, 47.0013936, -121.4194398, 'false')," +
                    "(2, 47.5776813, -121.7745689, 'true')," +
                    "(4, 48.3978238, -122.1406025, 'true');";

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_CAR);
        db.execSQL(SQL_CREATE_ENTRIES_TRIP);
        db.execSQL(SQL_CREATE_ENTRIES_STATION);
        db.execSQL(SQL_INSERT_USER);
        db.execSQL(SQL_INSERT_CAR);
        db.execSQL(SQL_INSERT_TRIP);
        db.execSQL(SQL_INSERT_STATION);
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
