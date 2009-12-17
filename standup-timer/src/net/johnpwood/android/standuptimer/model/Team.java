package net.johnpwood.android.standuptimer.model;

import java.util.List;

import net.johnpwood.android.standuptimer.dao.DAOFactory;
import net.johnpwood.android.standuptimer.dao.TeamDAO;
import net.johnpwood.android.standuptimer.utils.Logger;
import android.content.Context;

public class Team {
    private Long id = null;
    private String name = null;
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    public Team(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Team name must not be null");
        }
        this.name = name.trim();
    }

    public Team(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void delete(Context context) {
        TeamDAO dao = null;
        try {
            dao = daoFactory.getTeamDAO(context);
            dao.delete(this);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public static void create(String name, Context context) {
        TeamDAO dao = null;
        try {
            dao = daoFactory.getTeamDAO(context);
            dao.save(new Team(name));
        } catch (Exception e) {
            Logger.e(e.getMessage());
        } finally {
            dao.close();
        }
    }

    public static Team findByName(String teamName, Context context) {
        TeamDAO dao = null;
        try {
            dao = daoFactory.getTeamDAO(context);
            return dao.findByName(teamName);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public static List<String> findAllTeamNames(Context context) {
        TeamDAO dao = null;
        try {
            dao = daoFactory.getTeamDAO(context);
            return dao.findAllTeamNames();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
}
