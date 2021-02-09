package alyona.mikhaylova.service;

import alyona.mikhaylova.model.Point;
import alyona.mikhaylova.model.PointObject;
import alyona.mikhaylova.model.User;
import alyona.mikhaylova.repository.PointRepository;
import alyona.mikhaylova.repository.UserRepository;
import alyona.mikhaylova.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {
    @Autowired
    PointRepository pointRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    public List<Point> getAllPoints() {
        return this.pointRepository.findAll();
    }

    public PointObject addPoint(PointObject point, String header) {
        String token = jwtUtil.getTokenFromHeader(header);
        String login = jwtUtil.extractUsername(token);
        User user = userRepository.findByLogin(login);
        Point new_point = new Point();
        new_point.setX(point.getX());
        new_point.setY(point.getY());
        new_point.setR(point.getR());
        new_point.setUser(user);
        new_point.setHit(isHit(point));
        pointRepository.save(new_point);
        point.setHit(isHit(point)? "yes" : "no");
        return point;

    }


    public List<PointObject> getPoints(double r, String header) {
        String token = jwtUtil.getTokenFromHeader(header);
        String login = jwtUtil.extractUsername(token);
        List<Point> points = pointRepository.findByUserLogin(login);

        List<PointObject> points_for_client = new ArrayList<>();

        for (Point point : points) {
            PointObject p = new PointObject();
            p.setX(point.getX());
            p.setY(point.getY());
            p.setR(point.getR());
            p.setHit(point.getHit()? "yes" : "no");
            points_for_client.add(p);
        }

        return points_for_client;
    }

    private boolean isHit (PointObject point){
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        return ((Math.pow(y, 2) <= Math.pow(r/2, 2) - Math.pow(x, 2)) && (y <= 0) && (x >= 0)) ||
                ((y <= r) && (y >= 0) && (x >= 0) && (x <= r/2)) ||
                ((y <= x + r) && (x <= 0) && (y >= 0));
    }


}