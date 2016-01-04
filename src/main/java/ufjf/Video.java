package ufjf;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class Video {

    private String id;

    private String title;

    private String course;

    private Date date;

    private ArrayList<String> keywords;

    private String _abstract;

    private String description;

    private String publischer;

    private String creator;

    private String licence;

    private String language;

    private String educationLevel;

    private ArrayList<String> references;

    private int totalCategoriaRelacionadas;

    public void setVideosRelacionados(ArrayList<String> videosRelacionados) {
        this.videosRelacionados = videosRelacionados;
    }

    private ArrayList<String> categories;

    private ArrayList<String> videosRelacionados;

    public Video(String id) throws UnsupportedEncodingException {
        this.id = id;
        keywords = new ArrayList<String>();
        references = new ArrayList<String>();
        videosRelacionados = new ArrayList<>();
        totalCategoriaRelacionadas = 0;


        setReferences(GetVideosUFJF.getRefences(id));

        setCategories(GetVideosUFJF.getCategories(id));

        /*
        setTitle(GetVideosUFJF.getTitle(id));

        setCourse(GetVideosUFJF.getCourse(id));

        setCreator(GetVideosUFJF.getCreator(id));

        setDate(GetVideosUFJF.getDate(id));

        setKeywords(GetVideosUFJF.getKeywords(id));

        set_abstract(GetVideosUFJF.getAbstract(id));

        setLanguage(GetVideosUFJF.getLanguage(id));

        setLicence(GetVideosUFJF.getLicense(id));

        setPublischer(GetVideosUFJF.getPublischer(id));
        */
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String couse) {
        this.course = couse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublischer() {
        return publischer;
    }

    public void setPublischer(String publischer) {
        this.publischer = publischer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addReference(String reference){
        references.add(reference);
    }

    public void addKeyword(String keyword){
        keywords.add(keyword);
    }

    public void addRelacionado(String idVideoRelacionado){
        if (id.equals(idVideoRelacionado)) return;

        if (!videosRelacionados.contains(idVideoRelacionado)) {
            videosRelacionados.add(idVideoRelacionado);
        }
    }

    public ArrayList<String> getVideosRelacionados(){
        return videosRelacionados;
    }

    public void incTotalCategoriaRelacionadas(){
        totalCategoriaRelacionadas++;
    }

    public void setTotalCategoriaRelacionadas(int total){
        totalCategoriaRelacionadas = total;
    }

    public int getTotalCategoriaRelacionadas(){
        return totalCategoriaRelacionadas;
    }

}
