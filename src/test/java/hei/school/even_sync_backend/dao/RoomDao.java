package hei.school.even_sync_backend.dao;

import hei.school.even_sync_backend.datasource.DataSource;
import hei.school.even_sync_backend.model.Room;

import java.sql.*;
import java.util.*;

public class RoomDao {

    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM room");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rooms;
    }

    public void save(Room room) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO room(name) VALUES (?)"
            );
            ps.setString(1, room.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
