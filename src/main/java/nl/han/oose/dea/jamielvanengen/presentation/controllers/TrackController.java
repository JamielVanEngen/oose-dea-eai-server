package nl.han.oose.dea.jamielvanengen.presentation.controllers;

import nl.han.oose.dea.jamielvanengen.constants.HttpResponse;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistOverview;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackOverview;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackViewModel;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.builders.TrackViewModelBuilder;
import nl.han.oose.dea.jamielvanengen.services.TokenService;
import nl.han.oose.dea.jamielvanengen.services.TrackService;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/playlists")
public class TrackController {

    @Inject
    TokenService tokenService;

    @Inject
    TrackService trackService;

    @Inject
    TrackViewModelBuilder trackViewModelBuilder;

    @GET
    @Path("/")
    public Response getAllTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist) {
        if (tokenService.doesTokenExist(token)) {
            TrackOverview overview = getTracksByPlaylistId(forPlaylist);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    private TrackOverview getTracksByPlaylistId(int playlistId) {
        List<Track> tracks = trackService.getAllTracksByPlaylistId(playlistId);
        return new TrackOverview(trackViewModelBuilder.buildTrackViewModelsFromTracks(tracks));
    }
}
