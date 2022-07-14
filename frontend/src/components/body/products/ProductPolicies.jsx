import React from "react";
import "../../../css/body/products/ProductPolicies.css";

export const ProductPolicies = ({product}) => {

  const policiesNormas = product.policies.filter(policy=>policy.idPolicyTpe === 1)
  const policiesSeguridad = product.policies.filter(policy=>policy.idPolicyTpe=== 2)
  const policiesPoliticas = product.policies.filter(policy=>policy.idPolicyTpe === 3)

  

  return (
    <div>
      
      <div className="container-product-policies">
        <h3 className="product-policies-title">¿Qué tenés que saber?</h3>
        <hr className="product-policies-hr" />
        <div className="container-policies">
          <div className="container-policy">
            <p className="product-policies-text">Normas de la casa</p>
            <ul className="products-policies">
              {
                policiesNormas.map((policy)=>{
                  return <li className="product-policy" key={policy.id}>{policy.description}</li>
                })
              }
            </ul>
          </div>

          <div className="container-policy">
            <p className="product-policies-text">Salud y seguridad</p>
            <ul className="products-policies">
            {
              policiesSeguridad.map((policy)=>{
                return <li className="product-policy" key={policy.id}>{policy.description}</li>
              })
            }
            </ul>
          </div>

          <div className="container-policy">
            <p className="product-policies-text">Política de cancelación</p>
            <ul className="products-policies">
            {
              policiesPoliticas.map((policy)=>{
                return <li className="product-policy" key={policy.id}>{policy.description}</li>
              })
            }
            </ul>
          </div>
        </div>
      </div>

    </div>
  );
};
