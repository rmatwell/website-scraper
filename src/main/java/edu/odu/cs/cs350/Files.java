package edu.odu.cs.cs350;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class Files {

    private Set<AnchorFile> archive = new HashSet<AnchorFile>();

    private Set<AnchorFile> video = new HashSet<AnchorFile>();

    private Set<AnchorFile> audio = new HashSet<AnchorFile>();

    private Set<AnchorFile> other = new HashSet<AnchorFile>();

    public Files() {

    }

    public Set<AnchorFile> getArchive() {
        return archive;
    }

    public void setArchive(Set<AnchorFile> archive) {
        this.archive = archive;
    }

    public Set<AnchorFile> getVideo() {
        return video;
    }

    public void addVideo(AnchorFile video) {
        this.video.add(video);
    }

    public void setVideo(Set<AnchorFile> video) {
        this.video = video;
    }

    public Set<AnchorFile> getAudio() {
        return audio;
    }

    public void setAudio(Set<AnchorFile> audio) {
        this.audio = audio;
    }

    public Set<AnchorFile> getOther() {
        return other;
    }

    public void setOther(Set<AnchorFile> other) {
        this.other = other;
    }


}
