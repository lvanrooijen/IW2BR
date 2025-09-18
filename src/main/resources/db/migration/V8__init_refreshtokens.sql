CREATE TABLE IF NOT EXISTS refresh_tokens(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    token VARCHAR(512),
    revoked BOOLEAN,
    expires_at DATE,
    user_id UUID REFERENCES public.users(id)
);