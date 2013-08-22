#version 330

in vec3 positionView;
in vec2 textureUV;
in mat3 tangentMatrix;

layout(location = 0) out vec4 outputColor;
layout(location = 1) out vec4 outputNormal;
layout(location = 2) out vec3 outputMaterial;

uniform sampler2D diffuse;
uniform sampler2D normals;
uniform sampler2D specular;
uniform float diffuseIntensity;
uniform float ambientIntensity;

void main() {
    outputColor = texture(diffuse, textureUV);

    vec3 normalView = tangentMatrix * (texture(normals, textureUV).xyz * 2 - 1);
    outputNormal = vec4((normalView + 1) / 2, 1);

    float specularIntensity = texture(specular, textureUV).r;
    outputMaterial = vec3(diffuseIntensity, specularIntensity, ambientIntensity);
}
