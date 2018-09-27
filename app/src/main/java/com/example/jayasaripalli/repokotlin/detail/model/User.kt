package com.example.jayasaripalli.repokotlin.detail.model

class User{

    private var login: String? = null

    private var id: Int = 0

    private var node_id: String? = null

    private var avatar_url: String? = null

    private var gravatar_id: String? = null

    private var url: String? = null

    private var html_url: String? = null

    private var followers_url: String? = null

    private var following_url: String? = null

    private var gists_url: String? = null

    private var starred_url: String? = null

    private var subscriptions_url: String? = null

    private var organizations_url: String? = null

    private var repos_url: String? = null

    private var events_url: String? = null

    private var received_events_url: String? = null

    private var type: String? = null

    private var site_admin: Boolean = false

    private var name: String? = null

    private var company: String? = null

    private var blog: String? = null

    private var location: String? = null

    private var email: String? = null

    private var hireable: String? = null

    private var bio: String? = null

    private var public_repos: Int = 0

    private var public_gists: Int = 0

    private var followers: Int = 0

    private var following: Int = 0

    private var created_at: String? = null

    private var updated_at: String? = null

    fun setLogin(login: String) {
        this.login = login
    }

    fun getLogin(): String? {
        return this.login
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return this.id
    }

    fun setNode_id(node_id: String) {
        this.node_id = node_id
    }

    fun getNode_id(): String? {
        return this.node_id
    }

    fun setAvatar_url(avatar_url: String) {
        this.avatar_url = avatar_url
    }

    fun getAvatar_url(): String? {
        return this.avatar_url
    }

    fun setGravatar_id(gravatar_id: String) {
        this.gravatar_id = gravatar_id
    }

    fun getGravatar_id(): String? {
        return this.gravatar_id
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getUrl(): String? {
        return this.url
    }

    fun setHtml_url(html_url: String) {
        this.html_url = html_url
    }

    fun getHtml_url(): String? {
        return this.html_url
    }

    fun setFollowers_url(followers_url: String) {
        this.followers_url = followers_url
    }

    fun getFollowers_url(): String? {
        return this.followers_url
    }

    fun setFollowing_url(following_url: String) {
        this.following_url = following_url
    }

    fun getFollowing_url(): String? {
        return this.following_url
    }

    fun setGists_url(gists_url: String) {
        this.gists_url = gists_url
    }

    fun getGists_url(): String? {
        return this.gists_url
    }

    fun setStarred_url(starred_url: String) {
        this.starred_url = starred_url
    }

    fun getStarred_url(): String? {
        return this.starred_url
    }

    fun setSubscriptions_url(subscriptions_url: String) {
        this.subscriptions_url = subscriptions_url
    }

    fun getSubscriptions_url(): String? {
        return this.subscriptions_url
    }

    fun setOrganizations_url(organizations_url: String) {
        this.organizations_url = organizations_url
    }

    fun getOrganizations_url(): String? {
        return this.organizations_url
    }

    fun setRepos_url(repos_url: String) {
        this.repos_url = repos_url
    }

    fun getRepos_url(): String? {
        return this.repos_url
    }

    fun setEvents_url(events_url: String) {
        this.events_url = events_url
    }

    fun getEvents_url(): String? {
        return this.events_url
    }

    fun setReceived_events_url(received_events_url: String) {
        this.received_events_url = received_events_url
    }

    fun getReceived_events_url(): String? {
        return this.received_events_url
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getType(): String? {
        return this.type
    }

    fun setSite_admin(site_admin: Boolean) {
        this.site_admin = site_admin
    }

    fun getSite_admin(): Boolean {
        return this.site_admin
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String? {
        return this.name
    }

    fun setCompany(company: String) {
        this.company = company
    }

    fun getCompany(): String? {
        return this.company
    }

    fun setBlog(blog: String) {
        this.blog = blog
    }

    fun getBlog(): String? {
        return this.blog
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun getLocation(): String? {
        return this.location
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String? {
        return this.email
    }

    fun setHireable(hireable: String) {
        this.hireable = hireable
    }

    fun getHireable(): String? {
        return this.hireable
    }

    fun setBio(bio: String) {
        this.bio = bio
    }

    fun getBio(): String? {
        return this.bio
    }

    fun setPublic_repos(public_repos: Int) {
        this.public_repos = public_repos
    }

    fun getPublic_repos(): Int {
        return this.public_repos
    }

    fun setPublic_gists(public_gists: Int) {
        this.public_gists = public_gists
    }

    fun getPublic_gists(): Int {
        return this.public_gists
    }

    fun setFollowers(followers: Int) {
        this.followers = followers
    }

    fun getFollowers(): Int {
        return this.followers
    }

    fun setFollowing(following: Int) {
        this.following = following
    }

    fun getFollowing(): Int {
        return this.following
    }

    fun setCreated_at(created_at: String) {
        this.created_at = created_at
    }

    fun getCreated_at(): String? {
        return this.created_at
    }

    fun setUpdated_at(updated_at: String) {
        this.updated_at = updated_at
    }

    fun getUpdated_at(): String? {
        return this.updated_at
    }
}