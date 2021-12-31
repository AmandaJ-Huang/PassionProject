package datascraper.ignore;

import datascraper.ignore.utils.XmlParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataScraper implements Runnable {
    private XmlParser xmlParser;

    private String mysqlDb = "jdbc:mysql://localhost:3306/boardgames?autoReconnect=true&useSSL=false";
    private String mysqlUser = "potato";
    private String mysqlPwd = "taters";

    private String bggUrl = "https://boardgamegeek.com/boardgame/";

    public void run() {
        String sqlStmt = "INSERT IGNORE into " +
                "bg(bgId, thumbnail, gameurl, primaryname, yearpublished, minplayers, maxplayers, " +
                "playingtime, minplaytime, maxplaytime, minage, category, user_ratings, average_rating, difficulty) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY " +
                "UPDATE bgId=bgId, thumbnail=thumbnail, gameurl=gameurl, primaryname=primaryname, " +
                "yearpublished=yearpublished, minplayers=minplayers, maxplayers=maxplayers, " +
                "playingtime=playingtime, minplaytime=minplaytime, maxplaytime=maxplaytime, minage=minage, " +
                "category=category, user_ratings=user_ratings, average_rating=average_rating, difficulty=difficulty";
        try {
            Connection conn = DriverManager.getConnection(mysqlDb, mysqlUser, mysqlPwd);
            PreparedStatement ps = conn.prepareStatement(sqlStmt);
            /**
             * BGG XML API will return HTTP response codes 429 (Too Many Requests), 500 (Internal Server Error)
             * or 503 (Service Unavailable)
             * A 5-second delay is needed between each request
             * Cannot realistically request 300k+ boardgames via bgg
             */
            for (int i = 0; i < 5; i++) {
                xmlParser = new XmlParser(String.valueOf(i));

                if (xmlParser.isBoardGame()) {
                    ps.setLong(1, i);
                    ps.setString(2, xmlParser.getThumbnailUrl());
                    ps.setString(3, (bggUrl + i));
                    ps.setString(4, xmlParser.getPrimaryName());
                    ps.setInt(5, Integer.parseInt(xmlParser.getYearPublished()));
                    ps.setInt(6, Integer.parseInt(xmlParser.getMinPlayers()));
                    ps.setInt(7, Integer.parseInt(xmlParser.getMaxPlayers()));
                    ps.setInt(8, Integer.parseInt(xmlParser.getPlayingTime()));
                    ps.setInt(9, Integer.parseInt(xmlParser.getMinPlaytime()));
                    ps.setInt(10, Integer.parseInt(xmlParser.getMaxPlaytime()));
                    ps.setInt(11, Integer.parseInt(xmlParser.getMinAge()));
                    ps.setString(12, xmlParser.getGameCategories());
                    ps.setLong(13, Long.parseLong(xmlParser.getUsersRated()));
                    ps.setFloat(14, Float.parseFloat(xmlParser.getAverageRating()));
                    ps.setFloat(15, Float.parseFloat(xmlParser.getDifficulty()));
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
