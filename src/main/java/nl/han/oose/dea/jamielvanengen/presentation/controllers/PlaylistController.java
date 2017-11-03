package nl.han.oose.dea.jamielvanengen.presentation.controllers;

import nl.han.oose.dea.jamielvanengen.constants.HttpResponse;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistOverview;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistOverviewItem;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackOverview;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.builders.PlaylistOverviewItemBuilder;
import nl.han.oose.dea.jamielvanengen.services.PlaylistService;
import nl.han.oose.dea.jamielvanengen.services.TokenService;
import nl.han.oose.dea.jamielvanengen.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController {

    @Inject
    PlaylistService playlistService;

    @Inject
    TokenService tokenService;

    @Inject
    TrackService trackService;

    @Inject
    PlaylistOverviewItemBuilder playlistOverviewItemBuilder;

    @Inject
    TrackController trackController;

    @GET
    @Path("/")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (tokenService.doesTokenExist(token)) {
            int currentUserId = tokenService.getUserIdByTokenUuid(token);
            PlaylistOverview overview = getPlaylistOverview(currentUserId);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        if (tokenService.doesTokenExist(token)) {
            int userId = tokenService.getUserIdByTokenUuid(token);
            if (isUserThePlaylistOwner(userId, id)) {
                playlistService.deletePlaylistByid(id);
                PlaylistOverview overview = getPlaylistOverview(userId);

                return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
            }
        }
        return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
    }

    private PlaylistOverview getPlaylistOverview(int currentUserId) {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        int afspeelduur = trackService.getTotalTrackTime();
        List<PlaylistOverviewItem> playlistOverviewItems = playlistOverviewItemBuilder
                .buildPlaylistOverviewsFromPlaylists(playlists, currentUserId);

        return new PlaylistOverview(playlistOverviewItems, afspeelduur);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPlaylists(@PathParam("id") int id,
                                  @QueryParam("token") String token,
                                  PlaylistOverviewItem playlist) {
        if (tokenService.doesTokenExist(token)) {
            int userId = tokenService.getUserIdByTokenUuid(token);
            if (isUserThePlaylistOwner(userId, playlist.getId())) {
                playlistService.editPlaylist(playlist.getId(), playlist.getName());
                PlaylistOverview overview = getPlaylistOverview(userId);

                return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
            }
        }
        return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
    }

    private boolean isUserThePlaylistOwner(int userId, int playlistId) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);

        return playlist.getUserid() == userId;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylists(@QueryParam("token") String token, PlaylistOverviewItem playlist) {
        if (tokenService.doesTokenExist(token)) {
            int currentUserId = tokenService.getUserIdByTokenUuid(token);
            playlistService.addPlaylist(playlist.getName(), currentUserId);
            PlaylistOverview overview = getPlaylistOverview(currentUserId);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    public Response getAllTracksNotInPlaylist(@QueryParam("token") String token, @PathParam("id") int forPlaylist) {
        if (tokenService.doesTokenExist(token)) {
            TrackOverview overview = getAllTracksNotInPlaylist(forPlaylist);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    private TrackOverview getAllTracksNotInPlaylist(int playlistId) {
        return null;
    }
}
