INSERT INTO public.numbers(name, description, hotel_id, photo_count)
SELECT 'number-' || num, 'best number!', num2, 1
FROM generate_series(1, 200000) num,
     generate_series(1, 5) num2;