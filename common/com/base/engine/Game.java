package com.base.engine;

public class Game {

    float temp = 0.0f;
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Texture texture;
    private Camera camera;

    public Game() {
        mesh = new Mesh();//ResourceLoader.loadMesh("cube.obj");
        texture = ResourceLoader.loadTexture("test.png");
        shader = new Shader();
        camera = new Camera();

        Vertex[] vertices = new Vertex[]{
                new Vertex(new Vector3f(1, -1, -1), new Vector2f(0.25f,0.5f)),          //0
                new Vertex(new Vector3f(1, -1, 1), new Vector2f(0.5f,0.5f)),            //1
                new Vertex(new Vector3f(-1, -1, 1), new Vector2f(0.5f,0.75f)),          //2
                new Vertex(new Vector3f(-1, -1, -1), new Vector2f(0.25f,0.75f)),        //3
                new Vertex(new Vector3f(1, 1, -0.999999f), new Vector2f(0.25f,0.25f)),  //4
                new Vertex(new Vector3f(0.999999f, 1, 1), new Vector2f(0.5f,0.25f)),    //5
                new Vertex(new Vector3f(-1, 1, 1), new Vector2f(0.25f,0)),              //6
                new Vertex(new Vector3f(-1, 1, -1), new Vector2f(0.5f,0))};             //7
        int[] indices = new int[]{
                 0, 1, 3,
                 4, 7, 5,
                 0, 4, 1,
                 1, 5, 2,
                 2, 6, 3,
                 4, 0, 7,
                 1, 2, 3,
                 7, 6, 5,
                 4, 5, 1,
                 5, 6, 2,
                 6, 7, 3,
                 0, 3, 7};
        mesh.addVertices(vertices, indices);

        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.00001f, 1000);
        Transform.setCamera(camera);
        transform = new Transform();

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        camera.input();
    }

    public void update() {
        temp += Time.getDelta();

        float sinTemp = (float) Math.sin(temp);

        transform.setTranslation(0, 0, 5);
        //transform.setRotation(0, sinTemp * 180, 0);
        //transform.setScale(0.7f * sinTemp,0.7f * sinTemp, 0.7f * sinTemp);
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        texture.bind();
        mesh.draw();
    }
}
